package com.yinhao.coroutinessample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yinhao.commonmodule.base.BaseViewModel
import com.yinhao.coroutinessample.data.model.phonenum.PhoneResult
import com.yinhao.coroutinessample.data.network.PhoneRepository
import kotlinx.coroutines.launch
import  com.yinhao.coroutinessample.core.Result

class MainViewModel(private val repository: PhoneRepository) : BaseViewModel() {

    private val _uiState = MutableLiveData<MainUiModel>()

    val uiState: LiveData<MainUiModel>
        get() = _uiState

    //从网络加载数据
    fun getPhoneData() {
        viewModelScope.launch {
            emitUiState(true)

            val result = repository.getPhoneData("13173102701")

            checkResult(result, {
                emitUiState(showSuccess = it)
            }, {
                emitUiState(showError = it)
            })
        }
    }

    private inline fun <T : Any> checkResult(
        result: Result<T>,
        success: (T) -> Unit,
        error: (String?) -> Unit
    ) {
        if (result is Result.Success) {
            success(result.data)
        } else if (result is Result.Error) {
            error(result.exception.message)
        }
    }

    private fun emitUiState(
        showProgress: Boolean = false,
        showSuccess: PhoneResult? = null,
        showError: String? = null
    ) {
        val uiModel = MainUiModel(showProgress, showSuccess, showError)
        _uiState.value = uiModel
    }
}

data class MainUiModel(
    val showProgress: Boolean,
    val showSuccess: PhoneResult?,
    val showError: String?
)
