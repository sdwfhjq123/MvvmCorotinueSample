package com.yinhao.commonmodule.base.widget.state

/**
 * author:  SHIGUANG
 * date:    2018/12/3
 * version: v1.0
 * ### description: 状态枚举
 */
enum class SLState(val value: Int) {
    ERROR(1),
    EMPTY(2),
    LOADING(3),
    CONTENT(0),
    CUSTOMIZE(4),
    NO_NETWORK(5),
    NOT_SIGNED(6),
    TOKEN_OVERTIME(7)
}
