package com.yinhao.coroutinessample.ui.main.three

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.yinhao.commonmodule.base.base.BaseFragment
import com.yinhao.coroutinessample.databinding.FragmentNotificationsBinding
import com.yinhao.coroutinessample.ui.main.MainSharedViewModel
import com.yinhao.coroutinessample.ui.main.one.NotificationsViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * author:  yinhao
 * date:    2020/4/11
 * version: v1.0
 * ### description:
 */
class NotificationsFragment : BaseFragment<NotificationsViewModel, MainSharedViewModel, FragmentNotificationsBinding>() {

    companion object {
        fun newInstance(): NotificationsFragment {
            return NotificationsFragment()
        }
    }

    override fun initViewModel(): NotificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)


    override fun initSharedViewModel(): MainSharedViewModel =ViewModelProvider(this).get(MainSharedViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentNotificationsBinding =
        FragmentNotificationsBinding.inflate(layoutInflater)

    override fun initWindowFlag() {
    }

    override fun initEvents() {
    }

    override fun start() {
    }
}