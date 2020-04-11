package com.yinhao.commonmodule.base.repository

/**
 * author:      SHIGUANG
 * date:        2018/10/18
 * version:     v1.0
 * ### description: 仓库错误
 */
data class RepositoryException
constructor(var errorCode: Int, var errorMessage: String = "") : Exception(errorMessage)
