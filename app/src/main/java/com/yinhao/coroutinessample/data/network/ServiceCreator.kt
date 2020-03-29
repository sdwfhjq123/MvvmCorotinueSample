package com.yinhao.coroutinessample.data.network

import com.yinhao.coroutinessample.common.ConstantValue
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceCreator {

    private const val BASE_URL = ConstantValue.BASE_URL

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggerInterceptor())
        .callTimeout(5, TimeUnit.SECONDS)
        .build()

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = builder.build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    /**
     * ### 添加头信息参数。
     * 接收多键值对[params]，first为name,second.m
     */

    /**
     * ### 日志
     */
    private fun loggerInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}