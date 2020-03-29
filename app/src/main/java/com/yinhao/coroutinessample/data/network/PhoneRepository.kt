package com.yinhao.coroutinessample.data.network

import com.yinhao.coroutinessample.base.BaseRepository
import com.yinhao.coroutinessample.core.Result
import com.yinhao.coroutinessample.data.model.phonenum.PhoneResult
import com.yinhao.coroutinessample.data.network.api.PhoneNumService

object PhoneRepository : BaseRepository() {
    suspend fun getPhoneData(phone: String): Result<PhoneResult> {
        //调用ApiService定义的接口方法
        return safeApiCall(call ={ requestPhoneData(phone)},errorMessage = "" )
    }

    private suspend fun requestPhoneData(phone: String):Result<PhoneResult>{
        val response = ServiceCreator.create(PhoneNumService::class.java).getPhoneData(phone)
       return executeResponse(response,{},{})
    }
}