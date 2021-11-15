package com.bearya.mobile.component

import com.bearya.mobile.data.bean.LoginBean
import com.bearya.mobile.data.bean.NewsBean
import com.bearya.mobile.tools.HttpResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

internal interface ApiService {

    //  模板
    //  @POST("")
    //  @FormUrlEncoded

    /**
     * 登录
     * @param mobile 手机号码
     * @param password 密码
     * @return 用户登录信息
     */
    @POST("v1/client/user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("mobile") mobile: String?,
        @Field("password") password: String?
    ): HttpResult<LoginBean>?

    /**
     * 退出登录
     * @param uid 用户登录返回数据中的id
     * @param token 用户登录返回数据中的token
     */
    @POST("v1/client/user/logout")
    @FormUrlEncoded
    suspend fun logout(@Field("user_id") uid: Int,
               @Field("token") token: String?
    ): HttpResult<Any>?

    //  首页信息 ?grade=1 ，3，4 ，5，6，7，8
    //  @POST("v2/material/index/moduleItem")
    //  @FormUrlEncoded

    //  机器设备列表
    //  @POST("v1/story/machine/list")
    //  @FormUrlEncoded

    //  写作技巧 ?type=writing_skill&id=&leaf=2
    //  @POST("v2/material/writingMaterialItem/today")
    //  @FormUrlEncoded

    //  亲子关系
    //  @POST("v1/story/baby/list")
    //  @FormUrlEncoded

    //
    //  @POST("v1/story/wisdom/recommend")
    //  @FormUrlEncoded

    //  ?category_id=39&is_multiple=0&keyword=&page=1&limit=100
    //  @POST("v2/material/materialTuringAlbum/index")
    //  @FormUrlEncoded

    //  课文 category_id=510&limit=12&pos=1&order=asc&tag_ids=
    //  @POST("v1/story/category/albums")
    //  @FormUrlEncoded

    //  范文 category_id=455&limit=12&pos=1&order=asc&tag_ids=
    //  @POST("v1/story/category/albums")
    //  @FormUrlEncoded

    //  名师 category_id=379&limit=12&pos=1&order=asc&tag_ids=
    //  @POST("v1/story/category/albums")
    //  @FormUrlEncoded

    //  听书 category_id=380&limit=12&pos=1&order=asc&tag_ids=
    //  @POST("v1/story/category/albums")
    //  @FormUrlEncoded

    //  等级作文 category_id=493&limit=12&pos=1&order=asc&tag_ids=
    //  @POST("v1/story/category/albums")
    //  @FormUrlEncoded

    //  单元作文 category_id=482&limit=12&pos=1&order=asc&tag_ids=&son_ids=500
    //  @POST("v1/story/category/albums")
    //  @FormUrlEncoded

    //  名校书单 category_id=498&limit=12&pos=1&order=asc&tag_ids=&son_ids=500
    //  @POST("v1/story/category/albums")
    //  @FormUrlEncoded

    //  精彩专辑 alias=topic&limit=10&pos=1&recommended=0
    //  @POST("v1/story/category/albums")
    //  @FormUrlEncoded

    //  何捷作文 category_id=379&limit=10&pos=1&order=asc&tag_ids=
    //  @POST("v1/story/category/albums")
    //  @FormUrlEncoded

    //  每日一听 limit=20&pos=1
    @POST("v1/story/news/list")
    @FormUrlEncoded
    suspend fun everydayStory(
        @Field("limit") limit: Int,
        @Field("pos") pos: Int
    ) : HttpResult<NewsBean>?

}