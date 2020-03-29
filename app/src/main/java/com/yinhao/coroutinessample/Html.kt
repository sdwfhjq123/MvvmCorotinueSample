package com.yinhao.coroutinessample

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author: 23545
 * @description please add a description here
 * @date: Create in 10:31 2020/3/28
 */
class Html {
    fun body() {
        print("init body")
    }
}

fun html(init: Html.() -> Unit) {
    init(Html())
}

//fun main() {
//    html { body() }
//}


fun main() = runBlocking {
    // start main coroutine
    launch {
        // launch new coroutine in background and continue
        delay(1000L)
        println("World!")
    }
    println("Hello,") // main coroutine continues here immediately
    delay(2000L)      // delaying for 2 seconds to keep JVM alive
}

