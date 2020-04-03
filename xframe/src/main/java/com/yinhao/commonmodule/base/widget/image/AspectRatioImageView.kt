package com.yinhao.commonmodule.base.widget.image

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.IntDef
import androidx.appcompat.widget.AppCompatImageView
import com.yinhao.commonmodule.R

/**
 * author:  SHIGUANG
 * date:    2019/3/26
 * version: v1.0
 * ### description: 比例imageView的kotlin版
 * 感谢: santalu
 * https://github.com/santalu/aspect-ratio-imageview
 */
class AspectRatioImageView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    AppCompatImageView(context, attrs, defStyleAttr) {

    private var aspect: Int = 0
    private var aspectRatio: Float = 0f

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        val style = getContext().obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView)
        try {
            aspect = style.getInt(R.styleable.AspectRatioImageView_ari_aspect, HEIGHT)
            aspectRatio = style.getFloat(R.styleable.AspectRatioImageView_ari_ratio, DEFAULT_RATIO)
        } finally {
            style.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val height = measuredHeight
        val width = measuredWidth
        when (aspect) {
            AUTO -> if (height > width) {
                if (width == 0) {
                    return
                }
                aspect = WIDTH
                aspectRatio = Math.round(height.toDouble() / width).toFloat()
                setMeasuredDimensionByHeight(height)
            } else {
                if (height == 0) {
                    return
                }
                aspect = HEIGHT
                aspectRatio = Math.round(width.toDouble() / height).toFloat()
                setMeasuredDimensionByWidth(width)
            }
            WIDTH -> setMeasuredDimensionByHeight(height)
            HEIGHT -> setMeasuredDimensionByWidth(width)
            else -> setMeasuredDimensionByWidth(width)
        }
    }

    private fun setMeasuredDimensionByWidth(width: Int) {
        setMeasuredDimension(width, (width * aspectRatio).toInt())
    }

    private fun setMeasuredDimensionByHeight(height: Int) {
        setMeasuredDimension((height * aspectRatio).toInt(), height)
    }

    fun getAspectRatio(): Float {
        return aspectRatio
    }

    fun setAspectRatio(ratio: Float) {
        aspectRatio = ratio
        requestLayout()
    }

    @Aspect
    fun getAspect(): Int {
        return aspect
    }

    fun setAspect(@Aspect aspect: Int) {
        this.aspect = aspect
        requestLayout()
    }

    companion object {
        const val AUTO: Int = 2
        const val WIDTH: Int = 0
        const val HEIGHT: Int = 1
        const val DEFAULT_RATIO: Float = 1f

        @IntDef(WIDTH, HEIGHT)
        @Retention(AnnotationRetention.SOURCE)
        annotation class Aspect
    }
}
