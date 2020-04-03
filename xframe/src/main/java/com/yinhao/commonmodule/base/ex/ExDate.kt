package com.yinhao.commonmodule.base.ex

import com.blankj.utilcode.util.TimeUtils
import java.util.*

/**
 * author:  SHIGUANG
 * date:    2019/10/3
 * version: v1.0
 * ### description: Date工具
 */

/**
 * ### 日期String转为Date
 * ####格式[pattern]默认为yyyy-MM-dd HH:mm:ss
 */
fun String.toDate(pattern: String = "yyyy-MM-dd HH:mm:ss") =
    TimeUtils.string2Date(this, pattern)

/**
 * ### 日期String转为毫秒
 * ####格式[pattern]默认为yyyy-MM-dd HH:mm:ss
 */
fun String.toDateMillis(pattern: String = "yyyy-MM-dd HH:mm:ss") =
    TimeUtils.string2Millis(this, pattern)

/**
 * ### 时间戳转为Date
 */
fun Long.toDate() = TimeUtils.millis2Date(this)

/**
 * ### 时间戳转为日期String
 * ####格式[pattern]默认为yyyy-MM-dd HH:mm:ss
 */
fun Long.toDateString(pattern: String = "yyyy-MM-dd HH:mm:ss") =
    TimeUtils.millis2String(this, pattern)

/**
 * ### 时间戳转为友好的时间表示
 * ####eg:刚刚/XXX秒前/XXX分钟前/今天15:32/昨天15:32/2016-10-15
 */
fun Long.toFriendlyTimeSpanByNow() =
    TimeUtils.getFriendlyTimeSpanByNow(this)

/**
 * ### Date转为日期String
 * ####格式[pattern]默认为yyyy-MM-dd HH:mm:ss
 */
fun Date.toDateString(pattern: String = "yyyy-MM-dd HH:mm:ss") =
    TimeUtils.date2String(this, pattern)

/**
 * ### Date为友好的时间表示
 * ####eg:刚刚/XXX秒前/XXX分钟前/今天15:32/昨天15:32/2016-10-15
 */
fun Date.toFriendlyTimeSpanByNow() =
    TimeUtils.getFriendlyTimeSpanByNow(this)
