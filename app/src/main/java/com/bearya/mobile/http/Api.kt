package com.bearya.mobile.http

import java.net.UnknownHostException

object Api : HttpRetrofit() {

    private val api: ApiService by lazy(mode = LazyThreadSafetyMode.NONE) {
        retrofit.create(ApiService::class.java)
    }

    private suspend fun <T> check(errorMessage: String? = null, function: suspend () -> HttpResult<T>?): T? {
        try {
            val invoke = function.invoke()
            if (invoke?.status != 1) {
                throw Exception(invoke?.text)
            }
            return invoke.data
        } catch(ex : UnknownHostException) {
            throw Exception("网络链接错误!")
        } catch(ex: Exception) {
            throw Exception(ex.message ?: errorMessage)
        }
    }

    suspend fun login(mobile: String, password: String) = check("登录失败") {
        api.login(mobile, password)
    }

    suspend fun logout(userId: Int, token: String) = check("登出失败") {
        api.logout(userId, token)
    }

    suspend fun everydayStory(limit: Int = 20, pos: Int = 1) = check {
        api.everydayStory(limit, pos)
    }

}