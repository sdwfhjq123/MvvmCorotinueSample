package com.yinhao.coroutinessample

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import androidx.multidex.MultiDex
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.Utils
import com.bumptech.glide.Glide
import com.jeremyliao.liveeventbus.LiveEventBus
import com.yinhao.coroutinessample.others.CommonMethod
import com.yinhao.coroutinessample.others.ConstantValues
import com.yinhao.coroutinessample.data.model.UserInfo
import com.yinhao.coroutinessample.data.network.RepositoryComponent

/**
 * author:  yinhao
 * date:    2020/4/2
 * version: v1.0
 * ### description:
 */

class App : Application() {
    var isUserSigned: Boolean = false
    private lateinit var currentUser: UserInfo

    companion object {
        @JvmStatic
        lateinit var instance: App
            private set
    }

    /**
     * ### 获取当前用户
     */
    fun getCurrentUser(): UserInfo {
        val userJson = SPUtils.getInstance(ConstantValues.PREF_USER_RECORD).getString("user")
        if ("not_sign" == currentUser.id || userJson.isEmpty()) {
            isUserSigned = false
            return currentUser
        }

        val recordUser: UserInfo? =
            RepositoryComponent.gson.fromJson(userJson, UserInfo::class.java)
        recordUser?.recordTime?.apply {
            if (this != 0L && System.currentTimeMillis() - this < 24 * 60 * 60 * 1000)
                currentUser = recordUser
        }
        return currentUser
    }

    /**
     * ### 设置当前用户
     */
    fun setCurrentUser(@NonNull userInfo: UserInfo) {
        if ("not_sign" != userInfo.id) isUserSigned = true
        currentUser = userInfo
        userInfo.recordTime = System.currentTimeMillis()
        SPUtils.getInstance(ConstantValues.PREF_USER_RECORD).clear()
        SPUtils.getInstance(ConstantValues.PREF_USER_RECORD)
            .put("user", RepositoryComponent.gson.toJson(currentUser))
    }

    /**
     * ### 当前用户登出
     */
    fun signOut() {
        isUserSigned = false
        currentUser = CommonMethod.createNotSignedUserInfo()
        SPUtils.getInstance("XFRAME2_USER_RECORD").clear()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Utils.init(this)
        initLiveEventBus()
        currentUser = CommonMethod.createNotSignedUserInfo()
    }

    private fun initLiveEventBus() {
        LiveEventBus.config().autoClear(true)
            .supportBroadcast(this)
            .lifecycleObserverAlwaysActive(true)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Glide.get(this).onTrimMemory(level)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base);
    }
}