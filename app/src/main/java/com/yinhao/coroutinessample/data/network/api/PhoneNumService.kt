package com.yinhao.coroutinessample.data.network.api

import com.yinhao.coroutinessample.data.Response
import com.yinhao.coroutinessample.data.model.phonenum.PhoneResult
import retrofit2.http.GET
import retrofit2.http.Query

interface PhoneNumService {

    @GET("/mobile/get")
    suspend fun getPhoneData(
        @Query("phone") phone: String,
        @Query("key") key: String = "7195e5698ff26ac6ff024bce2a0e6b57"
    ): Response<PhoneResult>
}