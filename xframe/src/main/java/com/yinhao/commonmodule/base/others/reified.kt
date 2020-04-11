package com.yinhao.commonmodule.base.others

import android.content.Context
import android.content.Intent

/**
 * author:  yinhao
 * date:    2020/4/7
 * version: v1.0
 * ### description:
 */

inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}

