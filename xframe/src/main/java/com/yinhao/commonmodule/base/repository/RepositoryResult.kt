package com.yinhao.commonmodule.base.repository
/**
 * author:      SHIGUANG
 * date:        2018/9/28
 * version:     v1.0
 * ### description: 数据获取结果
 */
data class RepositoryResult<T>
constructor(var success: Boolean = false, var statusCode: Int = 0) {

    var data: T? = null
    var message: String? = null

    constructor() : this(false, 0)
}
