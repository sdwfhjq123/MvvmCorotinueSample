package com.yinhao.commonmodule.base.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding

/**
 * author:  yinhao
 * date:    2020/4/1
 * version: v1.0
 * ### description:ViewBinding代理，用以处理生命周期
 */

class ViewBindingHolder<B : ViewBinding> : ViewBindingHolderInterface<B>, LifecycleObserver {
    override var viewBinding: B? = null
    private lateinit var owner: String
    private var lifecycle: Lifecycle? = null

    override fun bindViewBinding(binding: B, fragment: Fragment): View {
        this.viewBinding = binding
        lifecycle = fragment.viewLifecycleOwner.lifecycle
        lifecycle?.addObserver(this)
        owner = fragment::class.simpleName ?: "N/A"
        return binding.root
    }

    override fun bindViewBinding(binding: B, activity: AppCompatActivity): View {
        this.viewBinding = binding
        lifecycle = activity.lifecycle
        lifecycle?.addObserver(this)
        owner = activity::class.simpleName ?: "N/A"
        return binding.root
    }

    override fun requireViewBinding(block: (B.() -> Unit)?): B =
        viewBinding?.apply { block?.invoke(this) }
            ?: throw IllegalStateException("在生命周期之外访问了其ViewBinding: $owner")

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyView() {
        lifecycle?.removeObserver(this)
        viewBinding = null
        lifecycle = null
    }
}

interface ViewBindingHolderInterface<B : ViewBinding> {
    val viewBinding: B?

    /**
     * ### 绑定ViewBinding及其生命周期
     */
    fun bindViewBinding(binding: B, fragment: Fragment): View

    /**
     * ### 绑定ViewBinding及其生命周期
     */
    fun bindViewBinding(binding: B, activity: AppCompatActivity): View

    /**
     * ### 强制需求ViewBinding
     * 当可以确是时在Fragment的生命周期内使用时，可以直接调用此函数来获取非空ViewBinding，否则请使用 viewBinding?.xxx
     */
    fun requireViewBinding(block: (B.() -> Unit)? = null): B
}