package com.yinhao.coroutinessample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yinhao.coroutinessample.data.network.PhoneRepository

/**
 * @author: yinhao
 * @description please add a description here
 * @date: Create in 15:17 2020/3/27
 */
class MainModelFactory(private val repository: PhoneRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T

    }
}