package team.fcma.xframe.ex

import android.view.View
import android.view.ViewGroup
import com.yinhao.commonmodule.base.others.XFConstants

/**
 * author:  SHIGUANG
 * date:    2019/10/3
 * version: v1.0
 * ### description: View工具类
 */


fun View.setGone() {
    this.visibility = View.GONE
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setInvisible() {
    this.visibility = View.INVISIBLE
}

val View.isGone: Boolean
    get() {
        return this.visibility == View.GONE
    }

val View.isVisible: Boolean
    get() {
        return this.visibility == View.VISIBLE
    }

val View.isInvisible: Boolean
    get() {
        return this.visibility == View.INVISIBLE
    }

/**
 * 设置View的高度[height]
 */
fun View.height(height: Int): View {
    val params = this.layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.height = height
    this.layoutParams = params
    return this
}

/**
 * ### 设置View的宽度[width]
 */
fun View.width(width: Int): View {
    val params = this.layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.width = width
    this.layoutParams = params
    return this
}

/**
 * ### 设置View的宽度[width]和高度[height]
 */
fun View.widthAndHeight(width: Int, height: Int): View {
    val params = this.layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.width = width
    params.height = height
    this.layoutParams = params
    return this
}

/**
 * ### 设置View的margin
 */
fun View.margin(
    leftMargin: Int = Int.MAX_VALUE,
    topMargin: Int = Int.MAX_VALUE,
    rightMargin: Int = Int.MAX_VALUE,
    bottomMargin: Int = Int.MAX_VALUE
): View {
    val params = this.layoutParams as ViewGroup.MarginLayoutParams
    if (leftMargin != Int.MAX_VALUE)
        params.leftMargin = leftMargin
    if (topMargin != Int.MAX_VALUE)
        params.topMargin = topMargin
    if (rightMargin != Int.MAX_VALUE)
        params.rightMargin = rightMargin
    if (bottomMargin != Int.MAX_VALUE)
        params.bottomMargin = bottomMargin
    this.layoutParams = params
    return this
}

/**
 * ### 设置点击监听
 */
var viewClickFlag = false
var clickRunnable = Runnable { viewClickFlag = false }
fun View.click(action: (view: View) -> Unit) {
    setOnClickListener {
        if (!viewClickFlag) {
            viewClickFlag = true
            action(it)
        }
        removeCallbacks(clickRunnable)
        postDelayed(clickRunnable, XFConstants.TIME_DELAY_CLICK)
    }
}

/**
 * ### 设置长按监听
 */
fun View.longClick(action: (view: View) -> Boolean) {
    setOnLongClickListener {
        action(it)
    }
}
