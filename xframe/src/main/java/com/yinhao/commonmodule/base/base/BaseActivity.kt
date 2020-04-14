package com.yinhao.commonmodule.base.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.blankj.utilcode.util.ActivityUtils
import com.gyf.immersionbar.ktx.immersionBar
import com.kaopiz.kprogresshud.KProgressHUD
import com.yinhao.commonmodule.R

/**
 * author:  yinhao
 * date:    2020/4/2
 * version: v1.0
 * ### description:
 */

abstract class BaseActivity<M : BaseViewModel, B : ViewBinding>
    : AppCompatActivity(), ViewBindingHolderInterface<B> by ViewBindingHolder<B>() {

    open var barDarkMode = false
    private var waitingView: KProgressHUD? = null
    private var noNetworkAlert: MaterialDialog? = null
    private var notSignedAlert: MaterialDialog? = null
    private var tokenOvertimeAlert: MaterialDialog? = null
    protected val viewModel by lazy { initViewModel() }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        ActivityUtils.getActivityList().add(this)
        setContentView(bindViewBinding(initViewBinging(layoutInflater), this))
        initWindowFlag()
        setupStatusBar()
        initEvents()
        start()
    }

    override fun onDestroy() {
        waitingView?.dismiss()
        noNetworkAlert?.dismiss()
        notSignedAlert?.dismiss()
        tokenOvertimeAlert?.dismiss()
        waitingView = null
        noNetworkAlert = null
        notSignedAlert = null
        tokenOvertimeAlert = null
        super.onDestroy()
        ActivityUtils.getActivityList().remove(this)
    }

    /**
     * ### 设置statusBar
     */
    private fun setupStatusBar() {
        immersionBar {
            keyboardEnable(true)
            statusBarDarkFont(barDarkMode)
        }}

    /**
     * ### 获取本页面对应的ViewModel
     */
    abstract fun initViewModel(): M

    /**
     * ### 获取本页面对应的ViewBinding
     */
    abstract fun initViewBinging(inflater: LayoutInflater): B

    /**
     *  ### 设置本页面的WindowFlag
     */
    abstract fun initWindowFlag()

    /**
     * ### 初始化事件
     */
    abstract fun initEvents()

    /**
     * ### 开始本页面业务逻辑
     */
    abstract fun start()

}