package com.yinhao.coroutinessample.logic.network

import androidx.room.Room
import com.blankj.utilcode.util.CacheDiskUtils
import com.blankj.utilcode.util.CacheMemoryUtils
import com.blankj.utilcode.util.Utils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yinhao.coroutinessample.logic.db.LocalDB
import java.io.File

/**
 * author:  SHIGUANG
 * date:    2019/3/26
 * version: v1.0
 * ### description: 仓库组件
 */
object RepositoryComponent {

    /**
     * ### 获取gson
     */
    val gson: Gson by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create()
    }

    /**
     * ### 获取本地数据库
     */
    val localDB: LocalDB by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        Room.databaseBuilder(Utils.getApp(), LocalDB::class.java, "TRAINING.db")
            .allowMainThreadQueries().build()
    }

    /**
     * ### 获取本地磁盘缓存
     */
    val diskCache: CacheDiskUtils by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        CacheDiskUtils.getInstance(File(Utils.getApp().cacheDir.absolutePath))
    }

    /**
     * ### 获取内存缓存
     */
    val memoryCache: CacheMemoryUtils by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        CacheMemoryUtils.getInstance()
    }
}
