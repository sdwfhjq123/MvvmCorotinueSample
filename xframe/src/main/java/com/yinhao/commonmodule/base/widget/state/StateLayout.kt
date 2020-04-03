package com.yinhao.commonmodule.base.widget.state

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.yinhao.commonmodule.R

/**
 * author:  SHIGUANG
 * date:    2018/12/3
 * version: v1.0
 * ### description:
 */
class StateLayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    FrameLayout(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    var currentState = SLState.CONTENT

    private lateinit var infoView: View
    private lateinit var loadingView: View
    private lateinit var contentView: View
    private var customizeView: View? = null
    private var currentShowView: View? = null
    private var pageClickHandler: StatePageClickHandler? = null

    private var defaultShow = 0
    private var customizeResId = 0
    private var useChangeAnim = false
    private var showAnimation: Animation? = null
    private var hideAnimation: Animation? = null
    private var errorImg = R.drawable.xf_sl_default_img_error
    private var emptyImg = R.drawable.xf_sl_default_img_empty
    private var noNetWorkImg = R.drawable.xf_sl_default_img_no_network
    private var notSignedImg = R.drawable.xf_sl_default_img_not_signed
    private var tokenOverTimeImg = R.drawable.xf_sl_default_img_token_over_time
    private var errorMsg = getContext().getString(R.string.xf_sl_error)
    private var emptyMsg = getContext().getString(R.string.xf_sl_empty)
    private var noNetWorkMsg = getContext().getString(R.string.xf_sl_noNetwork)
    private var notSignedMsg = getContext().getString(R.string.xf_sl_notSigned)
    private var tokenOverTimeMsg = getContext().getString(R.string.xf_sl_tokenOverTime)

    init {
        val style = getContext().obtainStyledAttributes(attrs, R.styleable.StateLayout)
        try {
            defaultShow = style.getInt(R.styleable.StateLayout_xf_sl_defaultShow, 0)
            useChangeAnim = style.getBoolean(R.styleable.StateLayout_xf_sl_useChangeAnim, false)
            customizeResId = style.getResourceId(R.styleable.StateLayout_xf_sl_customizeLayout, 0)

            errorMsg = style.getString(R.styleable.StateLayout_xf_sl_errorText) ?: errorMsg
            emptyMsg = style.getString(R.styleable.StateLayout_xf_sl_emptyText) ?: emptyMsg
            noNetWorkMsg =
                style.getString(R.styleable.StateLayout_xf_sl_noNetworkText) ?: noNetWorkMsg
            notSignedMsg =
                style.getString(R.styleable.StateLayout_xf_sl_notSignedText) ?: notSignedMsg
            tokenOverTimeMsg =
                style.getString(R.styleable.StateLayout_xf_sl_tokenOverTimeText)
                    ?: tokenOverTimeMsg

            errorImg = style.getResourceId(
                R.styleable.StateLayout_xf_sl_errorImage,
                R.drawable.xf_sl_default_img_error
            )
            emptyImg = style.getResourceId(
                R.styleable.StateLayout_xf_sl_emptyImage,
                R.drawable.xf_sl_default_img_empty
            )
            noNetWorkImg = style.getResourceId(
                R.styleable.StateLayout_xf_sl_noNetworkImage,
                R.drawable.xf_sl_default_img_no_network
            )
            notSignedImg = style.getResourceId(
                R.styleable.StateLayout_xf_sl_notSignedImage,
                R.drawable.xf_sl_default_img_not_signed
            )
            tokenOverTimeImg = style.getResourceId(
                R.styleable.StateLayout_xf_sl_tokenOverTimeImage,
                R.drawable.xf_sl_default_img_token_over_time
            )
            showAnimation = AnimationUtils.loadAnimation(
                context,
                style.getResourceId(R.styleable.StateLayout_xf_sl_showAnim, android.R.anim.fade_in)
            )
            hideAnimation = AnimationUtils.loadAnimation(
                context,
                style.getResourceId(
                    R.styleable.StateLayout_xf_sl_hideAnim,
                    android.R.anim.fade_out
                )
            )
        } finally {
            style.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount > 1) throw IllegalStateException("StateLayout只能有一个子控件")
        if (isInEditMode) return
        val inflater = LayoutInflater.from(context)
        contentView = getChildAt(0)
        inflater.inflate(R.layout.xf_sl_layout_info, this, true)
        inflater.inflate(R.layout.xf_sl_layout_loading, this, true)
        infoView = findViewById(R.id.layout_stateLayout_info)
        loadingView = findViewById(R.id.layout_stateLayout_loading)
        if (customizeResId != 0) {
            customizeView = inflater.inflate(customizeResId, null, false)
            addView(customizeView)
        }
        infoView.setOnClickListener {
            when (currentState) {
                SLState.ERROR -> pageClickHandler?.handleError()
                SLState.EMPTY -> pageClickHandler?.handleEmpty()
                SLState.NO_NETWORK -> pageClickHandler?.handleNoNetwork()
                SLState.NOT_SIGNED -> pageClickHandler?.handleNotSigned()
                SLState.TOKEN_OVERTIME -> pageClickHandler?.handleTokenOverTime()
                else -> pageClickHandler?.handleUnknownState(currentState)
            }
        }
        currentState = when (defaultShow) {
            0 -> SLState.CONTENT
            1 -> SLState.ERROR
            2 -> SLState.EMPTY
            3 -> SLState.LOADING
            4 -> SLState.CUSTOMIZE
            5 -> SLState.NO_NETWORK
            6 -> SLState.NOT_SIGNED
            7 -> SLState.TOKEN_OVERTIME
            else -> SLState.ERROR
        }
        when (currentState) {
            SLState.CONTENT -> {
                contentView.visibility = View.VISIBLE
                infoView.visibility = View.GONE
                loadingView.visibility = View.GONE
                customizeView?.visibility = View.GONE
                currentShowView = contentView
            }
            SLState.LOADING -> {
                loadingView.visibility = View.VISIBLE
                infoView.visibility = View.GONE
                contentView.visibility = View.GONE
                customizeView?.visibility = View.GONE
                currentShowView = loadingView
            }
            SLState.CUSTOMIZE -> {
                customizeView?.visibility = View.VISIBLE
                infoView.visibility = View.GONE
                contentView.visibility = View.GONE
                loadingView.visibility = View.GONE
                currentShowView = customizeView
            }
            else -> {
                infoView.visibility = View.VISIBLE
                contentView.visibility = View.GONE
                loadingView.visibility = View.GONE
                customizeView?.visibility = View.GONE
                currentShowView = infoView
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clearAllPageViewAnim()
        removeAllViews()
    }

    /**
     * ### 显示内容页
     */
    fun showContentView() {
        if (currentState == SLState.CONTENT) {
            return
        }
        changePageView(contentView, SLState.CONTENT, 0, "")
    }

    /**
     * ### 显示加载中
     */
    fun showLoadingView() {
        if (currentState == SLState.LOADING) {
            return
        }
        changePageView(loadingView, SLState.LOADING, 0, "")
    }

    /**
     * ### 显示自定义页
     */
    fun showCustomizeView() {
        if (customizeView == null) throw IllegalStateException("StateLayout中未配置CustomizeView")
        if (currentState == SLState.CUSTOMIZE) {
            return
        }
        changePageView(customizeView!!, SLState.CUSTOMIZE, 0, "")
    }

    /**
     * ### 显示错误页
     * [tipImg]为要显示的图片resId，[tipStr]为要显示的提示文字
     */
    fun showErrorView(tipImg: Int = errorImg, tipStr: String = errorMsg) {
        changePageView(infoView, SLState.ERROR, tipImg, tipStr)
    }

    /**
     * ### 显示没有数据页
     * [tipImg]为要显示的图片resId，[tipStr]为要显示的提示文字
     */
    fun showEmptyView(tipImg: Int = emptyImg, tipStr: String = emptyMsg) {
        changePageView(infoView, SLState.EMPTY, tipImg, tipStr)
    }

    /**
     * ### 显示没有网络页
     * [tipImg]为要显示的图片resId，[tipStr]为要显示的提示文字
     */
    fun showNoNetworkView(tipImg: Int = noNetWorkImg, tipStr: String = noNetWorkMsg) {
        changePageView(infoView, SLState.NO_NETWORK, tipImg, tipStr)
    }

    /**
     * ### 显示没有登录页
     * [tipImg]为要显示的图片resId，[tipStr]为要显示的提示文字
     */
    fun showNotSignedView(tipImg: Int = notSignedImg, tipStr: String = notSignedMsg) {
        changePageView(infoView, SLState.NOT_SIGNED, tipImg, tipStr)
    }

    /**
     * ### 显示token过期页
     * [tipImg]为要显示的图片resId，[tipStr]为要显示的提示文字
     */
    fun showTokenOvertimeView(tipImg: Int = tokenOverTimeImg, tipStr: String = tokenOverTimeMsg) {
        changePageView(infoView, SLState.TOKEN_OVERTIME, tipImg, tipStr)
    }

    /**
     * ### 获取自定义页面的根View
     */
    fun getCustomizeView(): View? {
        return customizeView
    }

    /**
     * ### 设置状态点击处理器
     * [handler]处理器
     */
    fun setStatePageClickHandler(handler: StatePageClickHandler) {
        this.pageClickHandler = handler
    }

    // 当动画持续时间短于状态变化请求时同步过渡动画
    private var animCounter: Int = 0

    /**
     * ### 清除所有页面的动画
     */
    private fun clearAllPageViewAnim() {
        infoView.clearAnimation()
        contentView.clearAnimation()
        loadingView.clearAnimation()
        customizeView?.clearAnimation()
    }

    /**
     * ### 变更页面
     */
    private fun changePageView(
        toShowView: View,
        toShowState: SLState,
        tipImg: Int,
        tipStr: String
    ) {
        if (useChangeAnim && showAnimation != null && hideAnimation != null) {
            clearAllPageViewAnim()
            val animCounterCopy = ++animCounter
            hideAnimation?.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationEnd(animation: Animation?) {
                    if (animCounterCopy != animCounter) return
                    if (toShowView == currentShowView && toShowView == infoView) {
                        infoView.findViewById<TextView>(R.id.textView_stateLayout_infoText).text =
                            tipStr
                        infoView.findViewById<ImageView>(R.id.imageView_stateLayout_infoImg)
                            .setImageResource(tipImg)
                    } else {
                        currentShowView?.visibility = View.GONE
                        toShowView.visibility = View.VISIBLE
                        infoView.findViewById<TextView>(R.id.textView_stateLayout_infoText)
                            .text = tipStr
                        infoView.findViewById<ImageView>(R.id.imageView_stateLayout_infoImg)
                            .setImageResource(tipImg)
                    }
                    currentShowView = toShowView
                    currentState = toShowState
                    toShowView.startAnimation(showAnimation)
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationStart(animation: Animation?) {
                }
            })
            currentShowView?.startAnimation(hideAnimation)
        } else {
            if (toShowView == infoView) {
                infoView.findViewById<TextView>(R.id.textView_stateLayout_infoText)
                    .text = tipStr
                infoView.findViewById<ImageView>(R.id.imageView_stateLayout_infoImg)
                    .setImageResource(tipImg)
            }
            toShowView.visibility = View.VISIBLE
            currentShowView?.visibility = View.GONE
            currentShowView = toShowView
            currentState = toShowState
        }
    }
}
