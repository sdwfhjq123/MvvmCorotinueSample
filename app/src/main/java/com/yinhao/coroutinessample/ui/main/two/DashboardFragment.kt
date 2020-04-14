package com.yinhao.coroutinessample.ui.main.two

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.yinhao.commonmodule.base.base.BaseFragment
import com.yinhao.coroutinessample.databinding.FragmentDashboardBinding
import com.yinhao.coroutinessample.ui.main.MainSharedViewModel
import com.yinhao.coroutinessample.ui.main.one.DashboardViewModel

/**
 * author:  yinhao
 * date:    2020/4/11
 * version: v1.0
 * ### description:
 */
class DashboardFragment : BaseFragment<DashboardViewModel, MainSharedViewModel, FragmentDashboardBinding>() {

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }

    override fun initViewModel(): DashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

    override fun initSharedViewModel(): MainSharedViewModel = ViewModelProvider(this).get(MainSharedViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDashboardBinding =
        FragmentDashboardBinding.inflate(layoutInflater)

    override fun initWindowFlag() {
    }

    override fun initEvents() {
    }

    override fun start() {
    }
}