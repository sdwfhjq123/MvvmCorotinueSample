package com.yinhao.commonmodule.base.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yinhao.commonmodule.base.repository.livedata.holder.StateHolder
import com.yinhao.commonmodule.base.repository.livedata.holder.WaitingHolder

/**
 * author:  yinhao
 * date:    2020/4/2
 * version: v1.0
 * ### description:
 */

open class BaseViewModel : ViewModel() {
    val notSignLiveData by lazy { MutableLiveData<String>() }
    val noNetworkLiveData by lazy { MutableLiveData<String>() }
    val showMessageLiveData by lazy { MutableLiveData<String>() }
    val tokenOverTimeLiveData by lazy { MutableLiveData<String>() }
    val stateLayoutLiveData by lazy { MutableLiveData<StateHolder>() }
    val waitingViewLiveData by lazy { MutableLiveData<WaitingHolder>() }
}