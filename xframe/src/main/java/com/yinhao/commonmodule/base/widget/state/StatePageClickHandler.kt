package com.yinhao.commonmodule.base.widget.state

import com.yinhao.commonmodule.base.widget.state.SLState

/**
 * author:  SHIGUANG
 * date:    2018/12/3
 * version: v1.0
 * ### description: 状态页面点击处理
 */
abstract class StatePageClickHandler {

    /**
     * ### 处理错误页点击
     */
    abstract fun handleError()

    /**
     * ### 处理暂无数据页点击
     */
    abstract fun handleEmpty()

    /**
     * ### 处理没有网络页点击
     */
    abstract fun handleNoNetwork()

    /**
     * ### 处理尚未登录页点击
     */
    abstract fun handleNotSigned()

    /**
     * ### 处理token过期页点击
     */
    abstract fun handleTokenOverTime()

    /**
     * ### 处理未知页面点击
     * [state]为当前状态
     */
    abstract fun handleUnknownState(state: SLState)
}
