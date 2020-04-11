package com.yinhao.commonmodule.base.base

import com.yinhao.commonmodule.R
import com.yinhao.commonmodule.base.repository.RepositoryException
import com.yinhao.commonmodule.base.repository.RepositoryResult
import com.yinhao.commonmodule.base.repository.livedata.LiveDataWrapper
import com.yinhao.commonmodule.base.repository.livedata.RNLDataWrapper
import com.yinhao.commonmodule.base.others.XFConstants
import org.jetbrains.annotations.NotNull
import org.json.JSONException
import java.io.EOFException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.ParseException

/**
 * author:  SHIGUANG
 * date:    2020/03/24
 * version: v1.0
 * ### description: 基础Repository
 */
open class BaseRepository {

    /**
     * ### 将任意对象转化为[RepositoryResult]
     * 如果有操作结果需求，需手动指定结果[success]、状态码[statusCode]和消息[message]。
     */
    protected fun <T : Any> T.toResult(
        success: Boolean = true, statusCode: Int = XFConstants.PACT_CODE_NORMAL, message: String = ""
    ): RepositoryResult<T> {
        val result: RepositoryResult<T> = RepositoryResult(success)
        result.data = this
        result.message = message
        result.statusCode = statusCode
        return result
    }

    /**
     * ### 将任意对象转化为[RepositoryResult]，附带对象的空判断。
     * 如果调用者[T]为null，手动指定的结果[success]、状态码[statusCode]和消息[message]。将被忽略并转为error。
     */
    protected fun <T : Any?> T.toResultWithNullCheck(
        success: Boolean = true, statusCode: Int = XFConstants.PACT_CODE_NORMAL, message: String = ""
    ): RepositoryResult<T> {
        val result: RepositoryResult<T> = RepositoryResult(success)
        result.data = this
        if (this != null && success) {
            result.message = message
            result.statusCode = statusCode
        } else {
            result.message = "data is null."
            result.statusCode = XFConstants.ERROR_CODE_NULLDATA
        }
        return result
    }

    /**
     * ### 对取数操作[request]做流程处理，返回[LiveDataWrapper]
     */
    protected suspend fun <T> dealWith(
        @NotNull request: suspend () -> RepositoryResult<T>
    ): LiveDataWrapper<T> {
        return try {
            val repositoryResult = request.invoke()
            if (repositoryResult.success && repositoryResult.statusCode == XFConstants.PACT_CODE_NORMAL) {
                LiveDataWrapper(repositoryResult.data)
            } else {
                LiveDataWrapper(handleErrorCode(repositoryResult.statusCode, repositoryResult.message))
            }
        } catch (e: Exception) {
            LiveDataWrapper(handlerUnknownError(e))
        }
    }

    /**
     * ### 对RNL取数操作[request]做流程处理，返回[RNLDataWrapper]
     */
    protected suspend fun <T> dealWithRNL(
        operation: Int, requestPage: Int, originalPage: Int,
        @NotNull request: suspend () -> RepositoryResult<T>
    ): RNLDataWrapper<T> {
        return try {
            val repositoryResult = request.invoke()
            if (repositoryResult.success && repositoryResult.statusCode == XFConstants.PACT_CODE_NORMAL) {
                RNLDataWrapper(repositoryResult.data, operation, requestPage, originalPage)
            } else {
                RNLDataWrapper(handleErrorCode(repositoryResult.statusCode, repositoryResult.message), operation, requestPage, originalPage)
            }
        } catch (e: Exception) {
            RNLDataWrapper(handlerUnknownError(e), operation, requestPage, originalPage)
        }
    }

    /**
     * ### 为上面的取数处理提供统一的错误处理
     */
    private fun handleErrorCode(statusCode: Int, message: String?): RepositoryException {
        return when (statusCode) {
            XFConstants.PACT_CODE_MISSING -> RepositoryException(
                XFConstants.ERROR_CODE_REMOTE,
                message ?: AppResources.getString(R.string.errorStr_cannotConnect)
            )
            XFConstants.PACT_CODE_FORBIDDEN -> RepositoryException(
                XFConstants.ERROR_CODE_REMOTE,
                message ?: AppResources.getString(R.string.errorStr_requestRefused)
            )
            XFConstants.PACT_CODE_TOKENOVERTIME -> RepositoryException(
                XFConstants.ERROR_CODE_TOKENOVERTIME,
                message ?: AppResources.getString(R.string.errorStr_tokenOverTime)
            )
            else -> RepositoryException(
                XFConstants.ERROR_CODE_UNKNOWN,
                message ?: AppResources.getString(R.string.errorStr_unknown)
            )
        }
    }

    /**
     * ### 为上面的取数处理提供统一的未知类型错误处理
     */
    private fun handlerUnknownError(error: Throwable): RepositoryException {
        return when (error) {
            is RepositoryException -> error
            is EOFException -> RepositoryException(
                XFConstants.ERROR_CODE_CONNECTINTERRUPT,
                AppResources.getString(R.string.errorStr_connectInterruption)
            )
            is ConnectException -> RepositoryException(
                XFConstants.ERROR_CODE_CANNOTCONNECT,
                AppResources.getString(R.string.errorStr_cannotConnect)
            )
            is SocketTimeoutException -> RepositoryException(
                XFConstants.ERROR_CODE_TIMEOUT,
                AppResources.getString(R.string.errorStr_timeOut)
            )
            is JSONException -> RepositoryException(
                XFConstants.ERROR_CODE_PARSE,
                AppResources.getString(R.string.errorStr_parse)
            )
            is ParseException -> RepositoryException(
                XFConstants.ERROR_CODE_PARSE,
                AppResources.getString(R.string.errorStr_parse)
            )
            else -> RepositoryException(
                XFConstants.ERROR_CODE_UNKNOWN,
                AppResources.getString(R.string.errorStr_unknown)
            )
        }
    }
}
