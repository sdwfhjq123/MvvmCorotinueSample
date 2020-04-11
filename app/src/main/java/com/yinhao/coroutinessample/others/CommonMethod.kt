package com.yinhao.coroutinessample.others

import android.content.Context
import android.content.res.Configuration
import com.yinhao.coroutinessample.logic.model.UserInfo


/**
 * author:  SHIGUANG
 * date:    2019/3/26
 * version: v1.0
 * ### description: 常用函数存放处
 */
object CommonMethod {

    /**
     * ### 创建尚未登录的用户信息
     */
    fun createNotSignedUserInfo(): UserInfo {
        val fakeUser =
            UserInfo("not_signed")
        fakeUser.name = "尚未登录"
        fakeUser.token = "not_signed"
        fakeUser.avatarUrl = "not_signed"
        fakeUser.gender = 2
        fakeUser.recordTime = System.currentTimeMillis()
        return UserInfo("not_signed")
    }

    /**
     * ### 判断是否是暗色模式
     */
    fun isDarkMode(context: Context): Boolean {
        val mode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return mode == Configuration.UI_MODE_NIGHT_YES
    }
}
