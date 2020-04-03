package com.yinhao.commonmodule.base.widget.toolbar

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.yinhao.commonmodule.R

/**
 * author:  SHIGUANG
 * date:    2019/3/26
 * version: v1.0
 * ### description:自定义Toolbar
 */
class XFToolbar(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        LinearLayoutCompat(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    lateinit var rootView: LinearLayoutCompat
    lateinit var titleView: AppCompatTextView
    lateinit var backView: AppCompatImageButton
    lateinit var operateView: AppCompatImageButton
    lateinit var txtOperateView: AppCompatTextView

    private var backVisible: Boolean = true
    private var titleVisible: Boolean = true
    private var operateVisible: Boolean = false
    private var txtOperateVisible: Boolean = false

    private var backSrc: Int = R.drawable.xf_icon_back
    private var operateSrc: Int = R.drawable.xf_icon_operate
    private var rootBackgroundSrc: Int = R.drawable.xf_toolbar_root_default_background

    private var titleText: String = getContext().getString(R.string.xf_tb_defaultTitle)
    private var txtOperateText: String = getContext().getString(R.string.xf_tb_defaultOperation)

    private var titleTextColor: Int =
            ContextCompat.getColor(getContext(), R.color.xf_tb_defaultTitleColor)
    private var txtOperateTextColor: Int =
            ContextCompat.getColor(getContext(), R.color.xf_defaultOperateColor)

    private var titleTextSize: Int =
            getContext().resources.getDimensionPixelSize(R.dimen.xf_tb_defaultTitleSize)
    private var txtOperateTextSize: Int =
            getContext().resources.getDimensionPixelSize(R.dimen.xf_tb_defaultOperationSize)

    init {
        LayoutInflater.from(context).inflate(R.layout.xf_toolbar_layout, this)
        val style = getContext().obtainStyledAttributes(attrs, R.styleable.XFToolbar)
        try {
            backVisible = style.getBoolean(R.styleable.XFToolbar_xf_tb_backVisible, true)
            titleVisible = style.getBoolean(R.styleable.XFToolbar_xf_tb_titleVisible, true)
            operateVisible = style.getBoolean(R.styleable.XFToolbar_xf_tb_operateVisible, false)
            txtOperateVisible = style.getBoolean(R.styleable.XFToolbar_xf_tb_txtOperateVisible, false)

            titleText = style.getString(R.styleable.XFToolbar_xf_tb_titleText) ?: titleText
            txtOperateText = style.getString(R.styleable.XFToolbar_xf_tb_txtOperateText)
                    ?: txtOperateText

            backSrc = style.getResourceId(
                    R.styleable.XFToolbar_xf_tb_backSrc,
                    R.drawable.xf_icon_back
            )
            operateSrc = style.getResourceId(
                    R.styleable.XFToolbar_xf_tb_operateSrc,
                    R.drawable.xf_icon_operate
            )
            rootBackgroundSrc = style.getResourceId(
                    R.styleable.XFToolbar_xf_tb_rootBackground,
                    R.drawable.xf_toolbar_root_default_background
            )

            titleTextSize = style.getDimensionPixelSize(
                    R.styleable.XFToolbar_xf_tb_titleTextSize,
                    getContext().resources.getDimensionPixelSize(R.dimen.xf_tb_defaultTitleSize)
            )
            txtOperateTextSize = style.getDimensionPixelSize(
                    R.styleable.XFToolbar_xf_tb_txtOperateTextSize,
                    getContext().resources.getDimensionPixelSize(R.dimen.xf_tb_defaultOperationSize)
            )
        } finally {
            style.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        rootView = findViewById(R.id.layout_XFToolbar_root)
        titleView = findViewById(R.id.textView_XFToolbar_title)
        backView = findViewById(R.id.imageViewButton_XFToolbar_back)
        txtOperateView = findViewById(R.id.textView_XFToolbar_txtOperate)
        operateView = findViewById(R.id.imageViewButton_XFToolbar_operate)

        backView.setImageResource(backSrc)
        operateView.setImageResource(operateSrc)
        rootView.setBackgroundResource(rootBackgroundSrc)

        titleView.text = titleText
        txtOperateView.text = txtOperateText

        titleView.setTextColor(titleTextColor)
        txtOperateView.setTextColor(txtOperateTextColor)

        titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize.toFloat())
        txtOperateView.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtOperateTextSize.toFloat())

        backView.visibility = if (backVisible) View.VISIBLE else View.GONE
        titleView.visibility = if (titleVisible) View.VISIBLE else View.GONE
        operateView.visibility = if (operateVisible) View.VISIBLE else View.GONE
        txtOperateView.visibility = if (txtOperateVisible) View.VISIBLE else View.GONE
    }
}
