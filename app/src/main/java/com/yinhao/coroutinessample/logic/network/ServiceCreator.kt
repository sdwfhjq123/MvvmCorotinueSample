package com.yinhao.coroutinessample.logic.network

import com.yinhao.commonmodule.base.ex.loggerInterceptor
import com.yinhao.coroutinessample.others.ConstantValues
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceCreator {

    private val httpClient = OkHttpClient.Builder()
        .apply {
           addInterceptor(loggerInterceptor())
            readTimeout(10L, TimeUnit.SECONDS)
            writeTimeout(10L, TimeUnit.SECONDS)
            connectTimeout(10L, TimeUnit.SECONDS)
            callTimeout(5, TimeUnit.SECONDS)
        }
        .build()

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)

    fun <T> create(
        serviceClass: Class<T>,
        retrofitConfig: ((Retrofit.Builder) -> Unit)? = null
    ): T {
        return builder.apply {
            addConverterFactory(GsonConverterFactory.create())
            //这里获取到token可以全局写一下
//            headerInterceptor()
            retrofitConfig
        }.build().create(serviceClass)
    }

    companion object {

        private const val BASE_URL = ConstantValues.BASE_URL

        private var network: ServiceCreator? = null

        fun getInstance(): ServiceCreator {
            if (network == null) {
                synchronized(ServiceCreator::class.java) {
                    if (network == null) {
                        network =
                            ServiceCreator()
                    }
                }
            }
            return network!!
        }

    }

}