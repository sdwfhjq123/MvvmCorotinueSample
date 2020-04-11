package com.yinhao.commonmodule.base.ex

import androidx.lifecycle.*
import com.yinhao.commonmodule.base.base.AppResources
import com.yinhao.commonmodule.base.others.XFConstants
import com.yinhao.commonmodule.base.repository.livedata.LiveDataWrapper
import com.yinhao.commonmodule.base.repository.livedata.holder.StateHolder
import com.yinhao.commonmodule.base.repository.livedata.holder.WaitingHolder
import com.yinhao.commonmodule.base.widget.state.SLState
import com.yinhao.commonmodule.R
import com.yinhao.commonmodule.base.repository.livedata.RNLDataWrapper

/**
 * ### State LiveData的post简写
 * 直接将状态[state]和信息[message]发送出去，不需要实例化一个StateHolder
 */
fun MutableLiveData<StateHolder>.setState(
    state: SLState, message: String = ""
) {
    value = StateHolder(state, message)
}

/**
 * ### State LiveData的post简写
 * 直接将状态[state]和信息[message]发送出去，不需要实例化一个StateHolder
 */
fun MutableLiveData<StateHolder>.postState(
    state: SLState, message: String = ""
) = postValue(StateHolder(state, message))

/**
 * ### Wait LiveData的post简写
 * 直接将是否显示[show]和信息[message]发送出去，不需要实例化一个WaitingHolder
 */
fun MutableLiveData<WaitingHolder>.setWaiting(
    show: Boolean, message: String = ""
) {
    value = WaitingHolder(show, message)
}

/**
 * ### Wait LiveData的post简写
 * 直接将是否显示[show]和信息[message]发送出去，不需要实例化一个WaitingHolder
 */
fun MutableLiveData<WaitingHolder>.postWaiting(
    show: Boolean, message: String = ""
) = postValue(WaitingHolder(show, message))

/**
 * ### 消息类LiveData的observe
 * [owner]为拥有者，[handle]中处理逻辑
 */
inline fun LiveData<String>.msgObserve(
    owner: LifecycleOwner,
    crossinline handle: (message: String) -> Unit = {}
) = observe(owner, Observer<String> { handle(it) })

/**
 * ### Wait LiveData的observe
 * [owner]为拥有者，[showWait]和[hideWait]分别处理显示和隐藏的逻辑
 */
inline fun LiveData<WaitingHolder>.waitObserve(
    owner: LifecycleOwner,
    crossinline showWait: (message: String) -> Unit = {},
    crossinline hideWait: (message: String) -> Unit = {}
) = observe(owner, Observer<WaitingHolder> {
    if (it.show) showWait(it.message)
    else hideWait(it.message)
})

/**
 * ### State LiveData的observe
 * [owner]为拥有者，[showError]、[showEmpty]、[showLoading]、[showContent]、[showCustomize]、
 * [showNoNetwork]、[showNotSigned]、[showTokenOverTime]分别处理对应状态的逻辑
 */
inline fun LiveData<StateHolder>.stateObserve(
    owner: LifecycleOwner,
    crossinline showError: (message: String) -> Unit = {},
    crossinline showEmpty: (message: String) -> Unit = {},
    crossinline showLoading: (message: String) -> Unit = {},
    crossinline showContent: (message: String) -> Unit = {},
    crossinline showCustomize: (message: String) -> Unit = {},
    crossinline showNoNetwork: (message: String) -> Unit = {},
    crossinline showNotSigned: (message: String) -> Unit = {},
    crossinline showTokenOverTime: (message: String) -> Unit = {}
) = observe(owner, Observer<StateHolder> {
    when (it.state) {
        SLState.ERROR -> showError(it.message)
        SLState.EMPTY -> showEmpty(it.message)
        SLState.LOADING -> showLoading(it.message)
        SLState.CONTENT -> showContent(it.message)
        SLState.CUSTOMIZE -> showCustomize(it.message)
        SLState.NO_NETWORK -> showNoNetwork(it.message)
        SLState.NOT_SIGNED -> showNotSigned(it.message)
        SLState.TOKEN_OVERTIME -> showTokenOverTime(it.message)
    }
})

/**
 * ### Repository的LiveData的observe简化。
 * [owner]为拥有者，[onSuccess]和[onFailed]中分别处理操作成功和失败的逻辑。
 */
inline fun <T : Any> LiveData<LiveDataWrapper<T>>.doObserve(
    owner: LifecycleOwner,
    crossinline onSuccess: (data: T?) -> Unit = {},
    crossinline onFailed: (code: Int, message: String) -> Unit = { _, _ -> }
) = observe(owner, Observer<LiveDataWrapper<T>> {
    if (it.success) onSuccess(it.data)
    else onFailed(
        it.error?.errorCode ?: XFConstants.ERROR_CODE_UNKNOWN,
        it.error?.errorMessage ?: AppResources.getString(R.string.errorStr_unknown)
    )
})

/**
 * ### Repository的RNL型LiveData的observe简化。
 * [owner]为拥有者，[onSuccess]和[onFailed]中分别处理操作成功和失败的逻辑,
 * 并在[onRestore]中进行状态恢复。
 */
inline fun <T : Any> LiveData<RNLDataWrapper<T>>.doObserve(
    owner: LifecycleOwner,
    crossinline onSuccess: (data: T?) -> Unit = {},
    crossinline onFailed: (code: Int, message: String) -> Unit = { _, _ -> },
    crossinline onRestore: (operation: Int, requestPage: Int, originalPage: Int) -> Unit = { _, _, _ -> }
) = observe(owner, Observer<RNLDataWrapper<T>> { wrapper ->
    if (wrapper.success) onSuccess(wrapper.data)
    else {
        onFailed(
            wrapper.error?.errorCode ?: XFConstants.ERROR_CODE_UNKNOWN,
            wrapper.error?.errorMessage ?: AppResources.getString(R.string.errorStr_unknown)
        )
        onRestore(wrapper.operation, wrapper.requestPage, wrapper.originalPage)
    }
})




