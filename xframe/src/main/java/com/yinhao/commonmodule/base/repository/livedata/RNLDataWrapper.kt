package com.yinhao.commonmodule.base.repository.livedata

import com.yinhao.commonmodule.base.repository.RepositoryException


/**
 * author:      SHIGUANG
 * date:        2018/10/18
 * version:     v1.0
 * ### description: RNLData数据Wrapper
 */
data class RNLDataWrapper<T>
constructor(
    val success: Boolean = false,
    var operation: Int = 0,
    var requestPage: Int = 0,
    var originalPage: Int = 0
) {

    var data: T? = null
    var error: RepositoryException? = null


    constructor(data: T?, operation: Int = 0, requestPage: Int = 0, originalPage: Int = 0)
            : this(true, operation, requestPage, originalPage) {
        this.data = data
    }

    constructor(
        error: RepositoryException,
        operation: Int = 0,
        requestPage: Int = 0,
        originalPage: Int = 0
    )
            : this(false, operation, requestPage, originalPage) {
        this.error = error
    }
}
