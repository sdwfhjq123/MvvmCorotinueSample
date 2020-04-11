package com.yinhao.commonmodule.base.repository.livedata

import com.yinhao.commonmodule.base.repository.RepositoryException


/**
 * author:      SHIGUANG
 * date:        2018/10/18
 * version:     v1.0
 * ### description: LiveData数据Wrapper
 */
data class LiveDataWrapper<T>
constructor(var success: Boolean) {

    var data: T? = null
    var error: RepositoryException? = null

    constructor(data: T?) : this(true) {
        this.data = data
    }

    constructor(error: RepositoryException) : this(false) {
        this.error = error
    }
}
