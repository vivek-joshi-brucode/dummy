//package com.example.dummy.ui.customview
//
//import android.animation.ValueAnimator
//import android.content.Context
//import android.graphics.Canvas
//import android.graphics.Paint
//import android.graphics.RectF
//import android.graphics.drawable.GradientDrawable
//import android.util.AttributeSet
//import android.view.MotionEvent
//import androidx.appcompat.widget.AppCompatButton
//import androidx.core.animation.addListener
//import androidx.core.content.ContextCompat
//
//class ProgressButton @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.buttonStyle
//) : AppCompatButton(context, attrs, defStyleAttr) {
//
//    private var progress = 0f
//    private var progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
//    private var progressRect = RectF()
//
//    private var animator: ValueAnimator? = null
//    private val animationDuration = 2000L // progress to complete in 2 seconds
//
//    private var onProgressCompleteListener: (() -> Unit)? = null
//
//    private var cornerRadius: Float = 16f // default corner radius
//    private var backgroundDrawable = GradientDrawable()
//
//    init {
//        // Set default progress color
//        progressPaint.color = ContextCompat.getColor(context, android.R.color.holo_blue_light)
//
//        // Setup background with default corner radius and transparent color
//        backgroundDrawable.cornerRadius = cornerRadius
//        backgroundDrawable.setColor(ContextCompat.getColor(context, android.R.color.transparent))
//        background = backgroundDrawable
//    }
//
//    override fun onDraw(canvas: Canvas) {
//        // Draw progress background with rounded corners
//        val widthProgress = width * (progress / 100f)
//        progressRect.set(0f, 0f, widthProgress, height.toFloat())
//        canvas.drawRoundRect(progressRect, cornerRadius, cornerRadius, progressPaint)
//
//        // Draw button text on top
//        super.onDraw(canvas)
//    }
//
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        when (event?.action) {
//            MotionEvent.ACTION_DOWN -> {
//                // Start progress animation
//                startProgressAnimation()
//                // Show press feedback
//                isPressed = true
//                invalidate()
//                return true
//            }
//            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
//                // Stop and reset progress if released early
//                if (animator?.isRunning == true) {
//                    animator?.cancel()
//                    resetProgress()
//                }
//                isPressed = false
//                invalidate()
//                return true
//            }
//        }
//        return super.onTouchEvent(event)
//    }
//
//    private fun startProgressAnimation() {
//        animator = ValueAnimator.ofFloat(0f, 100f).apply {
//            duration = animationDuration
//            addUpdateListener {
//                progress = it.animatedValue as Float
//                invalidate()
//            }
//
//            addListener(onEnd = {
//                if (progress == 100f) {
//                    onProgressCompleteListener?.invoke()
//                    resetProgress()
//                }
//            }, onCancel = {
//                resetProgress()
//            })
//            start()
//        }
//    }
//
//    private fun resetProgress() {
//        progress = 0f
//        invalidate()
//    }
//
//    fun setOnProgressCompleteListener(listener: () -> Unit) {
//        onProgressCompleteListener = listener
//    }
//
//    /**
//     * Set the progress color programmatically.
//     *
//     * @param color Color integer (e.g. ContextCompat.getColor(context, R.color.your_color))
//     */
//    fun setProgressColor(color: Int) {
//        progressPaint.color = color
//        invalidate()
//    }
//
//    /**
//     * Set the corner radius programmatically.
//     *
//     * @param radius Corner radius in pixels
//     */
//    fun setCornerRadius(radius: Float) {
//        cornerRadius = radius
//        backgroundDrawable.cornerRadius = radius
//        invalidate()
//    }
//}

package com.example.dummy.ui.customview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton
import androidx.core.animation.addListener
import androidx.core.content.ContextCompat

class ProgressButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.buttonStyle
) : AppCompatButton(context, attrs, defStyleAttr) {

    private var progress = 0f
    private val progressRect = RectF()

    private var animator: ValueAnimator? = null
    private val animationDuration = 2000L

    private var onProgressCompleteListener: (() -> Unit)? = null

    private var cornerRadius = 16f // Default corner radius

    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, android.R.color.holo_blue_light)
        style = Paint.Style.FILL
    }

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, android.R.color.transparent)
        style = Paint.Style.FILL
    }

    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, android.R.color.darker_gray)
        strokeWidth = 4f
        style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        val widthF = width.toFloat()
        val heightF = height.toFloat()

        // Draw background
        canvas.drawRoundRect(0f, 0f, widthF, heightF, cornerRadius, cornerRadius, backgroundPaint)

        // Draw progress
        val widthProgress = widthF * (progress / 100f)
        progressRect.set(0f, 0f, widthProgress, heightF)
        canvas.drawRoundRect(progressRect, cornerRadius, cornerRadius, progressPaint)

        // Draw stroke inset to match corners
        val halfStroke = strokePaint.strokeWidth / 2f
        canvas.drawRoundRect(
            halfStroke,
            halfStroke,
            widthF - halfStroke,
            heightF - halfStroke,
            cornerRadius,
            cornerRadius,
            strokePaint
        )

        // Draw text on top
        super.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                startProgressAnimation()
                isPressed = true
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (animator?.isRunning == true) {
                    animator?.cancel()
                    resetProgress()
                }
                isPressed = false
                invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun startProgressAnimation() {
        animator = ValueAnimator.ofFloat(0f, 100f).apply {
            duration = animationDuration
            addUpdateListener {
                progress = it.animatedValue as Float
                invalidate()
            }
            addListener(
                onEnd = {
                    if (progress == 100f) {
                        onProgressCompleteListener?.invoke()
                        resetProgress()
                    }
                },
                onCancel = {
                    resetProgress()
                }
            )
            start()
        }
    }

    private fun resetProgress() {
        progress = 0f
        invalidate()
    }

    fun setOnProgressCompleteListener(listener: () -> Unit) {
        onProgressCompleteListener = listener
    }

    fun setProgressColor(color: Int) {
        progressPaint.color = color
        invalidate()
    }

    fun setCornerRadius(radius: Float) {
        cornerRadius = radius
        invalidate()
    }

    fun setStrokeColor(color: Int) {
        strokePaint.color = color
        invalidate()
    }

    fun setStrokeWidth(width: Float) {
        strokePaint.strokeWidth = width
        invalidate()
    }

    fun setBackgroundColorCustom(color: Int) {
        backgroundPaint.color = color
        invalidate()
    }
}
