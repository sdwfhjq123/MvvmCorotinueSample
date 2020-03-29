package com.yinhao.coroutinessample.ui

import android.app.ProgressDialog
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yinhao.commonmodule.base.BaseVMActivity
import com.yinhao.coroutinessample.R
import com.yinhao.coroutinessample.data.network.PhoneRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseVMActivity<MainViewModel>() {

    override fun initVM() =
        ViewModelProvider(this, MainModelFactory(PhoneRepository))[MainViewModel::class.java]

    override fun initView() {
        //点击刷新按钮来网络加载d
        button.setOnClickListener {
            mViewModel.getPhoneData()
        }
    }

    override fun initData() {}

    override fun startObserve() {
        //对加载状态进行动态观察
        mViewModel.apply {
            uiState.observe(this@MainActivity, Observer {
                if (it.showProgress) showProgressDialog()

                it.showSuccess?.let {
                    dismissProgressDialog()
                    Toast.makeText(this@MainActivity, it.city, Toast.LENGTH_SHORT).show()
                }

                it.showError?.let { err ->
                    dismissProgressDialog()
                    Toast.makeText(this@MainActivity, err, Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

    private var progressDialog: ProgressDialog? = null
    private fun showProgressDialog() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(this)
        progressDialog?.show()
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }

    override fun getLayoutResId(): Int = R.layout.activity_main
}
