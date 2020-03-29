package com.yinhao.commonmodule.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *
 */
abstract class BaseVMActivity<VM : BaseViewModel> : AppCompatActivity() {

    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = initVM()

        startObserve()
        setContentView(getLayoutResId())
        initView()
        initData()
    }

    open fun getLayoutResId(): Int = 0
    abstract fun initVM(): VM
    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()

}