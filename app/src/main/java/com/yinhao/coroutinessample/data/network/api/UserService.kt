package com.yinhao.coroutinessample.data.network.api

import com.yinhao.commonmodule.base.arch.repository.RepositoryResult
import com.yinhao.coroutinessample.data.model.UserInfo
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * author:  SHIGUANG
 * date:    2019/3/26
 * version: v1.0
 * ### description: DefaultAPI接口列表
 */
interface UserService {

    /**
     * ### 执行登录
     * - [signName] 登录名
     * - [password] 密码
     */
    @FormUrlEncoded
    @POST("doLogin")
    suspend fun doSignIn(
            @Field("username") signName: String,
            @Field("password") password: String
    ): RepositoryResult<UserInfo>
}
