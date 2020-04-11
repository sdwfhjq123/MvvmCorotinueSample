package com.yinhao.coroutinessample.ui.main.one

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yinhao.commonmodule.base.base.BaseFragment
import com.yinhao.coroutinessample.databinding.FragmentTwoBinding
import com.yinhao.coroutinessample.ui.main.MainSharedViewModel

/**
 * author:  yinhao
 * date:    2020/4/11
 * version: v1.0
 * ### description:
 */
class TwoFragment:BaseFragment<TwoViewModel,MainSharedViewModel,FragmentTwoBinding>() {

    companion object{
        fun  newInstance(): TwoFragment {
            return TwoFragment()
        }
    }

    override fun initViewModel(): TwoViewModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initSharedViewModel(): MainSharedViewModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initViewBinging(inflater: LayoutInflater, container: ViewGroup?): FragmentTwoBinding {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initWindowFlag() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initEvents() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}