package com.yinhao.coroutinessample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.yinhao.commonmodule.base.arch.base.BaseFragmentActivity
import com.yinhao.coroutinessample.databinding.ActivityMainBinding
import com.yinhao.coroutinessample.ui.MainViewModel

class MainActivity :
    BaseFragmentActivity<MainViewModel, MainViewModelShared, ActivityMainBinding>() {

    override fun initViewModel(): MainViewModel =
        ViewModelProvider(this).get(MainViewModel::class.java)

    override fun initSharedViewModel(): MainViewModelShared =
        ViewModelProvider(this).get(MainViewModelShared::class.java)

    override fun initViewBinging(inflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(inflater)

    override fun dealWithOnCreateViewBond(
        savedInstanceState: Bundle?,
        viewBinding: ActivityMainBinding
    ) {

    }

    override fun initWindowFlag() {
    }

    override fun initEvents() {
    }

    override fun start() {
    }

    override fun setupFragments() {
    }

}
