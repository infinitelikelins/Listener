package com.bearya.mobile.tools

import android.os.Build
import com.bearya.mobile.data.repository.UserRepository
import com.bearya.mobile.listener.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class HttpRetrofit protected constructor() {

    private val okHttpClient: OkHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val cookieJar: CookieJar = object : CookieJar {
            private val cookieStore: MutableMap<String, List<Cookie>> = mutableMapOf()
            override fun loadForRequest(url: HttpUrl): List<Cookie> {
                return cookieStore[url.host] ?: emptyList()
            }

            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                cookieStore[url.host] = cookies
            }
        }

        OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .callTimeout(5, TimeUnit.SECONDS)
            .cookieJar(cookieJar)
            .addNetworkInterceptor { addHeaderInterceptor(it) }
            .apply { if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor) }
            .build()
    }

    protected val retrofit: Retrofit by lazy {
        Retrofit.Builder().client(okHttpClient).baseUrl(BuildConfig.ApiUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun addHeaderInterceptor(chain: Interceptor.Chain): Response =
        chain.proceed(chain.request().newBuilder().apply {
            addHeader("version", "${BuildConfig.VERSION_CODE}")
            addHeader("Version_Name", BuildConfig.VERSION_NAME)
            addHeader("appid",BuildConfig.APPLICATION_ID)
            addHeader("equip", Build.MODEL)
            addHeader("Connection", "close")
            addHeader("Content-Language","zh")
            addHeader("AUTHORIZATION", UserRepository.token)
            addHeader("User-Agent","00000000,unknown,${Build.BRAND},${Build.MODEL},Android${Build.VERSION.RELEASE}(${Build.VERSION.SDK_INT})")
        }.build())

}

data class HttpResult<T>(
    /** 服务器返回结果状态码  */
    var status: Int,
    /** 服务器返回的具体业务数据，为了统一处理，此处是指成功返回的结果模型  */
    var data: T?,
    /** 服务器信息  */
    var text: String,
    /** 状态码 */
    var status_code: Int?
) {

    override fun toString(): String {
        return "HttpResult = { status=${status} , statusCode=${status_code} , text=${text}' , data=${data}}"
    }

}