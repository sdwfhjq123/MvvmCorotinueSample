package com.yinhao.commonmodule.base.base

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.Utils

/**
 * author:  RD_CY
 * date:    2018/12/28
 * version: v1.0
 * ### description: 通过此类获取资源
 */
object AppResources {

    /**
     * ### 根据[resId]获取String
     */
    fun getString(@StringRes resId: Int): String {
        return Utils.getApp().resources.getString(resId)
    }

    /**
     * ### 根据[resId]获取String,并且使用[formatArgs]来替换格式化参数
     */
    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return Utils.getApp().resources.getString(resId, formatArgs)
    }

    /**
     * ### 根据[resId]获取StringArray
     */
    fun getStringArray(@ArrayRes resId: Int): Array<String> {
        return Utils.getApp().resources.getStringArray(resId)
    }

    /**
     * ### 根据[resId]获取Integer
     */
    fun getInt(@IntegerRes resId: Int): Int {
        return Utils.getApp().resources.getInteger(resId)
    }

    /**
     * ### 根据[resId]获取IntArray
     */
    fun getIntArray(@ArrayRes resId: Int): IntArray {
        return Utils.getApp().resources.getIntArray(resId)
    }

    /**
     * ### 根据[resId]获取颜色
     */
    fun getColor(@ColorRes resId: Int): Int {
        return ContextCompat.getColor(Utils.getApp(), resId)
    }

    /**
     * ### 根据[resId]获取颜色StateList
     */
    fun getColorStateList(@ColorRes resId: Int): ColorStateList? {
        return ContextCompat.getColorStateList(Utils.getApp(), resId)
    }

    /**
     * ### 根据[resId]获取Drawable
     */
    fun getDrawable(@DrawableRes resId: Int): Drawable? {
        return ContextCompat.getDrawable(Utils.getApp(), resId)
    }
}
