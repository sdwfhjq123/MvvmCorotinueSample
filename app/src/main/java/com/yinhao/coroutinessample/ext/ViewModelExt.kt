package com.yinhao.coroutinessample.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//fun ViewModel.lanuch(
//    block: suspend CoroutineScope.() -> Unit,
//    onError: (e: Throwable) -> Unit = {},
//    onComplete: () -> Unit = {}
//) {
//    viewModelScope.launch(CoroutineExceptionHandler { coroutineContext, e ->
//        onError(e)
//    }) {
//        try {
//            block.invoke(this)
//        } finally {
//            onComplete()
//        }
//    }
//}