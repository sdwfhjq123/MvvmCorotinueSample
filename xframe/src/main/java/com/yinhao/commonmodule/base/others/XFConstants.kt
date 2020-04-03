package com.yinhao.commonmodule.base.others

/**
 * author:  SHIGUANG
 * date:    2019/3/26
 * version: v1.0
 * ### description: 常量存放处
 */
class XFConstants {

    companion object {

        const val TIME_DELAY_CLICK = 250L
        const val TIME_THROTTLE_CLICK = 500L
        const val TIME_RECORD_INFO_KEEP = 7 * 1000 * 60 * 60 * 24L//登录后信息保存有效时长，单位 毫秒

        //全局LiveDataBus的value
        const val SLDB_VAL_SHOW_NO_NETWORK = "SCOPED_SHOW_NO_NETWORK"
        const val SLDB_VAL_SHOW_NOT_SIGNED = "SCOPED_SHOW_NOT_SIGNED"
        const val SLDB_VAL_SHOW_TOKEN_OVERTIME = "SCOPED_SHOW_TOKEN_OVERTIME"

        //内部使用错误码和错误信息
        const val ERROR_CODE_UNKNOWN = 1000
        const val ERROR_CODE_LOCAL = 1001
        const val ERROR_CODE_REMOTE = 1002
        const val ERROR_CODE_NULLDATA = 1003
        const val ERROR_CODE_PARSE = 1101
        const val ERROR_CODE_CIPHER = 1102
        const val ERROR_CODE_NONETWORK = 1201
        const val ERROR_CODE_NOTSIGNED = 1202
        const val ERROR_CODE_TOKENOVERTIME = 1203
        const val ERROR_CODE_TIMEOUT = 1301
        const val ERROR_CODE_CANNOTCONNECT = 1302
        const val ERROR_CODE_CONNECTINTERRUPT = 1304

        //约定特殊码
        const val PACT_CODE_NORMAL = 200
        const val PACT_CODE_MISSING = 404
        const val PACT_CODE_FORBIDDEN = 403
        const val PACT_CODE_TOKENOVERTIME = 202

    }
}
