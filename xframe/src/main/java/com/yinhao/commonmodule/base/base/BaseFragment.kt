package com.yinhao.commonmodule.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.yinhao.commonmodule.R
import me.yokeyword.fragmentation.SupportFragment

/**
 * author:      SHIGUANG
 * date:        2018/10/17
 * version:     v1.0
 * ### description: 基础Fragment
 */
abstract class BaseFragment<M : BaseViewModel, SM : BaseViewModel, B : ViewBinding>
    : SupportFragment(), ViewBindingHolderInterface<B> by ViewBindingHolder<B>() {

    open var barDarkMode = false
    protected val viewModel: M by lazy { initViewModel() }
    protected val sharedViewModel: SM by lazy { initSharedViewModel() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? =
        viewBinding?.root ?: bindViewBinding(initViewBinging(inflater, container), this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWindowFlag()
        view.findViewById<View>(R.id.view_XFToolbar_compatTopPadding)?.let {
            ImmersionBar.setStatusBarView(this, it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initEvents()
    }

    override fun onSupportVisible() {
        super.onSupportVisible()
        setupStatusBar()
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        start()
    }

    /**
     * ### 设置statusBar
     */
    private fun setupStatusBar() {
        view?.findViewById<View>(R.id.view_XFToolbar_compatTopPadding)?.let {
            immersionBar {
                keyboardEnable(true)
                statusBarDarkFont(barDarkMode)
            }
        }
    }

    /**
     * ### 建立共享ViewModel
     */
    protected fun setupSharedViewModel(block: (hostActivity: FragmentActivity) -> SM): SM {
        return activity?.run { block.invoke(this) }
            ?: throw IllegalStateException("未能找到对应的Activity：" + this@BaseFragment::class.simpleName)
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
    abstract fun initViewBinging(inflater: LayoutInflater, container: ViewGroup?): B

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
