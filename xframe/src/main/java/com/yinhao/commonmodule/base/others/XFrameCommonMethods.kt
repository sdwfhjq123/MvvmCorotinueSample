package team.fcma.xframe.others

import android.view.MotionEvent
import android.view.View
import android.widget.EditText

/**
 * author:  SHIGUANG
 * date:    2019/3/26
 * version: v1.0
 * ### description: 通用函数存放处
 */
object XFrameCommonMethods {

    /**
     * 判断是否需要隐藏键盘
     */
    fun isShouldHideKeyboard(view: View, event: MotionEvent): Boolean {
        if (view is EditText) {
            val location = IntArray(2)
            view.getLocationInWindow(location)
            val top = location[1]
            val left = location[0]
            val right = left + view.getWidth()
            val bottom = top + view.getHeight()
            return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
        }
        return false
    }
}
