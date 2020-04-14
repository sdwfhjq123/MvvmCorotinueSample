package com.yinhao.commonmodule.base.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.NetworkUtils
import com.gyf.immersionbar.ktx.immersionBar
import com.jeremyliao.liveeventbus.LiveEventBus
import com.kaopiz.kprogresshud.KProgressHUD
import com.yinhao.commonmodule.R
import com.yinhao.commonmodule.base.repository.livedata.holder.WaitingHolder
import com.yinhao.commonmodule.base.others.XFConstants
import team.fcma.xframe.others.XFrameCommonMethods

/**
 * author:  SHIGUANG
 * date:    2020/3/31
 * version: v1.0
 * ### description: 基础FragmentActivity
 */
abstract class BaseFragmentActivity<M : BaseViewModel, SM : BaseViewModel, B : ViewBinding>
    : AppCompatActivity(), ViewBindingHolderInterface<B> by ViewBindingHolder<B>() {

    private var waitingView: KProgressHUD? = null
    private var noNetworkAlert: MaterialDialog? = null
    private var notSignedAlert: MaterialDialog? = null
    private var tokenOvertimeAlert: MaterialDialog? = null
    protected val viewModel: M by lazy { initViewModel() }
    protected val sharedViewModel: SM by lazy { initSharedViewModel() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityUtils.getActivityList().add(this)
        setContentView(bindViewBinding(initViewBinding(layoutInflater), this))
        initWindowFlag()
        setupStatusBar()
        setupFragments()
        setupScopedEvent()
        initEvents()
        start()
    }

    /**
     * ### 销毁控件并从记录中移除
     */
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
     *  ### 自动隐藏软键盘
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        ev?.run {
            if (this.action == MotionEvent.ACTION_DOWN) currentFocus else null
        }?.let {
            if (XFrameCommonMethods.isShouldHideKeyboard(it, ev)) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        return super.dispatchTouchEvent(ev)
    }


    /**
     * ### 获取等待View
     */
    protected fun getWaitingView(): KProgressHUD {
        waitingView?.dismiss()
        waitingView = waitingView ?: (KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(false)
            .setLabel(getString(R.string.pleaseWaiting))
            .setAnimationSpeed(2)
            .setDimAmount(0.5f))
        return waitingView!!
    }

    /**
     * ### 隐藏等待View
     */
    protected fun hideWaitingView() {
        waitingView?.dismiss()
    }

    /**
     * ### 获取没有网络的提示框
     */
    protected fun getNoNetworkAlert(): MaterialDialog {
        noNetworkAlert?.dismiss()
        noNetworkAlert = noNetworkAlert ?: (MaterialDialog(this)
            .lifecycleOwner(this)
            .icon(res = R.drawable.xf_icon_common_alert)
            .title(res = R.string.noNetwork)
            .message(res = R.string.noNetwork_content)
            .negativeButton(R.string.cancel) { it.dismiss() }
            .positiveButton(R.string.goSetting) {
                it.dismiss()
                NetworkUtils.openWirelessSettings()
            })
        return noNetworkAlert!!
    }

    /**
     * ### 获取没有登录的提示框
     */
    protected fun getNotSignedAlert(): MaterialDialog {
        notSignedAlert?.dismiss()
        notSignedAlert = notSignedAlert ?: (MaterialDialog(this)
            .lifecycleOwner(this)
            .icon(res = R.drawable.xf_icon_common_alert)
            .title(res = R.string.notSigned)
            .message(res = R.string.notSigned_content)
            .negativeButton(R.string.cancel) { it.dismiss() }
            .positiveButton(R.string.goSignIn) {
                it.dismiss()
            })
        return notSignedAlert!!
    }

    /**
     * ### 获取token过期的提示框
     */
    protected fun getTokenOvertimeAlerter(): MaterialDialog {
        tokenOvertimeAlert?.dismiss()
        tokenOvertimeAlert = tokenOvertimeAlert ?: (MaterialDialog(this)
            .lifecycleOwner(this)
            .icon(res = R.drawable.xf_icon_common_alert)
            .title(res = R.string.tokenOvertime)
            .message(res = R.string.tokenOvertime_content)
            .negativeButton(R.string.cancel) { it.dismiss() }
            .positiveButton(R.string.reSignIn) {
                it.dismiss()
            })
        return tokenOvertimeAlert!!
    }

    /**
     * ### 设置statusBar
     */
    private fun setupStatusBar() {
//        findViewById<View>(R.id.view_XFToolbar_compatTopPadding)?.let {
//            immersionBar {
//                statusBarView(it)
//                keyboardEnable(true)
//                navigationBarEnable(false)
//            }
//        }
    }

    /**
     * ### 设置activity范围中的事件
     */
    private fun setupScopedEvent() {
        //设置WaitView事件
        LiveEventBus.get(this::class.simpleName, WaitingHolder::class.java).observe(this, Observer { msg ->
            if (!msg.show) hideWaitingView()
            else getWaitingView().apply { this.setDetailsLabel(msg.message) }.show()
        })
        //设置AlertView事件
        LiveEventBus.get(this::class.simpleName, String::class.java).observe(this, Observer { msg ->
            when (msg) {
                XFConstants.SLDB_VAL_SHOW_NOT_SIGNED -> viewModel.notSignLiveData.value = "NOT_SIGNED"
                XFConstants.SLDB_VAL_SHOW_NO_NETWORK -> viewModel.noNetworkLiveData.value = "NO_NETWORK"
                XFConstants.SLDB_VAL_SHOW_TOKEN_OVERTIME -> viewModel.tokenOverTimeLiveData.value = "TOKEN_OVERTIME"
            }
        })
    }

    /**
     * ### 获取本页面对应的ViewModel
     */
    abstract fun initViewModel(): M

    /**
     * ### 获取本页面对应的共享ViewModel
     */
    abstract fun initSharedViewModel(): SM

    /**
     * ### 获取本页面对应的ViewBinding
     */
    abstract fun initViewBinding(inflater: LayoutInflater): B

    /**
     *  ### 设置本页面的WindowFlag
     */
    abstract fun initWindowFlag()

    /**
     * ### 初始化当前页面包含的Fragments
     */
    abstract fun setupFragments()

    /**
     * ### 初始化事件
     */
    abstract fun initEvents()

    /**
     * ### 开始本页面业务逻辑
     */
    abstract fun start()
}
