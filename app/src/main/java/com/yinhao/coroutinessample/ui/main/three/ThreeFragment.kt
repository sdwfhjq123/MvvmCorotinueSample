package com.yinhao.coroutinessample.ui.main.one

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yinhao.commonmodule.base.base.BaseFragment
import com.yinhao.coroutinessample.databinding.FragmentThreeBinding
import com.yinhao.coroutinessample.ui.main.MainSharedViewModel

/**
 * author:  yinhao
 * date:    2020/4/11
 * version: v1.0
 * ### description:
 */
class ThreeFragment:BaseFragment<ThreeViewModel,MainSharedViewModel,FragmentThreeBinding>() {

    companion object{
        fun  newInstance(): ThreeFragment {
            return ThreeFragment()
        }
    }

    override fun initViewModel(): ThreeViewModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initSharedViewModel(): MainSharedViewModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initViewBinging(inflater: LayoutInflater, container: ViewGroup?): FragmentThreeBinding {
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