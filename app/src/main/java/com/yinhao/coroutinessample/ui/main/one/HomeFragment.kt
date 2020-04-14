package com.yinhao.coroutinessample.ui.main.one

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ToastUtils
import com.yinhao.commonmodule.base.base.BaseFragment
import com.yinhao.coroutinessample.databinding.FragmentHomeBinding
import com.yinhao.coroutinessample.ui.main.MainSharedViewModel
import team.fcma.xframe.ex.click

/**
 * author:  yinhao
 * date:    2020/4/11
 * version: v1.0
 * ### description:
 */
class HomeFragment : BaseFragment<HomeViewModel, MainSharedViewModel, FragmentHomeBinding>() {

    override fun initViewModel(): HomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

    override fun initSharedViewModel(): MainSharedViewModel = ViewModelProvider(this).get(MainSharedViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater, container, false)

    override fun initWindowFlag() {
    }

    override fun initEvents() {
    }

    override fun start() {
        viewBinding?.btn?.click { ToastUtils.showShort("123") }
    }
}