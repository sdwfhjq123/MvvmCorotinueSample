package com.yinhao.coroutinessample.ui.main

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.yinhao.commonmodule.base.base.BaseFragmentActivity
import com.yinhao.coroutinessample.databinding.ActivityMainBinding
import com.yinhao.coroutinessample.ui.MainViewModel
import com.yinhao.coroutinessample.ui.main.one.OneFragment
import com.yinhao.coroutinessample.ui.main.one.ThreeFragment
import com.yinhao.coroutinessample.ui.main.one.TwoFragment
import me.yokeyword.fragmentation.SupportFragment

class MainActivity : BaseFragmentActivity<MainViewModel, MainSharedViewModel, ActivityMainBinding>() {

    private var currentTabPos = 0
    private val fragments = arrayOfNulls<SupportFragment>(3)

    override fun initViewModel(): MainViewModel =
        ViewModelProvider(this).get(MainViewModel::class.java)

    override fun initSharedViewModel(): MainSharedViewModel =
        ViewModelProvider(this).get(MainSharedViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(inflater)

    override fun initWindowFlag() {
    }

    override fun setupFragments() {
        val firstFragment = findFragment(OneFragment::class.java)
        if (firstFragment != null) {
            fragments[0] = firstFragment
            fragments[1] = findFragment(TwoFragment::class.java)
            fragments[2] = findFragment(ThreeFragment::class.java)
        } else {
            fragments[0] = OneFragment.newInstance()
            fragments[1] = TwoFragment.newInstance()
            fragments[2] = ThreeFragment.newInstance()
        }
    }

    override fun initEvents() {
    }

    override fun start() {
    }

}
