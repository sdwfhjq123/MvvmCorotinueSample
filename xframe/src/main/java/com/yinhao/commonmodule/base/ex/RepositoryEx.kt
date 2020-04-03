package com.yinhao.commonmodule.base.ex

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * author:  yinhao
 * date:    2020/4/2
 * version: v1.0
 * ### description: 扩展类
 */

/**
 * ### 添加头信息参数。
 * 接收多键值对[params]，first为name,second.m
 */

/**
 * ### 日志
 */
 fun loggerInterceptor(): Interceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}

/**
 * ### 公共参数
 */
fun queryParameterInterceptor(vararg params: Pair<String, Any>): Interceptor {
    return Interceptor { chain ->
        val newUrlBuilder = chain.request().url.newBuilder()
        params.forEach { newUrlBuilder.addQueryParameter(it.first, it.second.toString()) }
        val newRequest = chain.request()
            .newBuilder()
            .url(newUrlBuilder.build())
            .build()
        chain.proceed(newRequest)
    }
}

/**
 * ### 头信息
 */
fun headerInterceptor(vararg params: Pair<String, Any>): Interceptor {
    return Interceptor { chain ->
        val newRequestBuilder = chain.request().newBuilder()
            .method(chain.request().method, chain.request().body)
        params.forEach { newRequestBuilder.header(it.first, it.second.toString()) }
        chain.proceed(newRequestBuilder.build())
    }
}

/**
 * ### 添加头信息参数。
 * 接收多键值对[params]，first为name,second.toString为value
 */
fun OkHttpClient.Builder.addHeaderInterceptor(
    vararg params: Pair<String, Any>
) = apply {
    this.addInterceptor { chain ->
        val newRequestBuilder = chain.request().newBuilder()
            .method(chain.request().method, chain.request().body)
        params.forEach { newRequestBuilder.header(it.first, it.second.toString()) }
        chain.proceed(newRequestBuilder.build())
    }
}

/**
 * ### 添加公共请求参数。
 * 接收多键值对[params]，first为name,second.toString为value
 */
fun OkHttpClient.Builder.addQueryParameterInterceptor(
    vararg params: Pair<String, Any>
) = apply {
    this.addInterceptor { chain ->
        val newUrlBuilder = chain.request().url.newBuilder()
        params.forEach { newUrlBuilder.addQueryParameter(it.first, it.second.toString()) }
        val newRequest = chain.request().newBuilder().url(newUrlBuilder.build()).build()
        chain.proceed(newRequest)
    }
}