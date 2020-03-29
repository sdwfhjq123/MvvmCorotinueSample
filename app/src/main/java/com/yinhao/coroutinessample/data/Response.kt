package com.yinhao.coroutinessample.data

data class Response<out T>(
    val resultcode: Int,
    val reason: String,
    val result: T
)