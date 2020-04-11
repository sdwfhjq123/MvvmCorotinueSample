package com.yinhao.coroutinessample.ui.main.one

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yinhao.commonmodule.base.base.BaseFragment
import com.yinhao.coroutinessample.databinding.FragmentOneBinding
import com.yinhao.coroutinessample.ui.main.MainSharedViewModel

/**
 * author:  yinhao
 * date:    2020/4/11
 * version: v1.0
 * ### description:
 */
class OneFragment:BaseFragment<OneViewModel,MainSharedViewModel,FragmentOneBinding>() {

    companion object{
        fun  newInstance(): OneFragment {
            return OneFragment()
        }
    }
    override fun initViewModel(): OneViewModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initSharedViewModel(): MainSharedViewModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initViewBinging(inflater: LayoutInflater, container: ViewGroup?): FragmentOneBinding {
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