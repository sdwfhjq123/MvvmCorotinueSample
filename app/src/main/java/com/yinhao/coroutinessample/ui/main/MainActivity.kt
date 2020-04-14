package com.yinhao.coroutinessample.ui.main

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.yinhao.commonmodule.base.base.BaseFragmentActivity
import com.yinhao.coroutinessample.R
import com.yinhao.coroutinessample.databinding.ActivityMainBinding
import com.yinhao.coroutinessample.ui.MainViewModel

class MainActivity : BaseFragmentActivity<MainViewModel, MainSharedViewModel, ActivityMainBinding>() {


    override fun initViewModel(): MainViewModel =
        ViewModelProvider(this).get(MainViewModel::class.java)

    override fun initSharedViewModel(): MainSharedViewModel =
        ViewModelProvider(this).get(MainSharedViewModel::class.java)

    override fun initViewBinding(inflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(inflater)

    override fun initWindowFlag() {
    }

    override fun setupFragments() {
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
//        setupActionBarWithNavController(navController, appBarConfiguration)
        viewBinding?.navView?.let { bnv ->
            bnv.setupWithNavController(navController)
            bnv.setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.navigation_home->navController.navigate(R.id.homeFragment)
                    R.id.navigation_dashboard->navController.navigate(R.id.dashboardFragment)
                    R.id.navigation_notifications->navController.navigate(R.id.notificationsFragment)
                }
                true
            }
        }
    }

    override fun initEvents() {
    }

    override fun start() {
    }

}
