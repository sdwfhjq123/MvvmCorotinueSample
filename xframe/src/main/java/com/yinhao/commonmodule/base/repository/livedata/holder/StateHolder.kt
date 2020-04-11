package com.yinhao.commonmodule.base.repository.livedata.holder

import com.yinhao.commonmodule.base.widget.state.SLState


/**
 * author:      SHIGUANG
 * date:        2018/10/8
 * version:     v1.0
 * ### description: 状态信息持有者
 */
data class StateHolder
constructor(val state: SLState, val message: String = "")
