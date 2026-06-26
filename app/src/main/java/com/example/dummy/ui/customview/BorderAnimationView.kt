package com.example.dummy.ui.customview

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View

/*
class BorderAnimationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = borderWidth
    }

    private var phase = 0f
    private var gradientColors = intArrayOf(Color.RED, Color.YELLOW, Color.GREEN)
    private val handler = Handler(Looper.getMainLooper())

    private val animationRunnable = object : Runnable {
        override fun run() {
            phase += 10f
            invalidate()
            handler.postDelayed(this, 30)
        }
    }

    companion object {
        private const val borderWidth = 10f
    }

    init {
        handler.post(animationRunnable)
    }

    fun setGradientColors(colors: IntArray) {
        gradientColors = colors
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val offset = borderWidth / 2
        val w = width.toFloat()
        val h = height.toFloat()

        val length = (w + h) * 2
        val phaseShift = phase % length

        // Top border (left to right)
        drawBorderSegment(canvas, offset, offset, w - offset, offset, phaseShift)

        // Right border (top to bottom)
        drawBorderSegment(canvas, w - offset, offset, w - offset, h - offset, (phaseShift - w).mod(length))

        // Bottom border (right to left)
        drawBorderSegment(canvas, w - offset, h - offset, offset, h - offset, (phaseShift - w - h).mod(length))

        // Left border (bottom to top)
        drawBorderSegment(canvas, offset, h - offset, offset, offset, (phaseShift - w - h - w).mod(length))
    }

    private fun drawBorderSegment(
        canvas: Canvas,
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        shift: Float
    ) {
        val gradient = LinearGradient(
            startX, startY, endX, endY,
            gradientColors, null, Shader.TileMode.MIRROR
        )

        val matrix = Matrix()
        matrix.setTranslate(shift, shift)
        gradient.setLocalMatrix(matrix)

        paint.shader = gradient
        canvas.drawLine(startX, startY, endX, endY, paint)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        handler.removeCallbacks(animationRunnable)
    }
}
*/

class BorderAnimationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint()
    private val strokeWidth = 10f
    private var phase = 0f
    private var color = Color.RED

    private val handler = Handler(Looper.getMainLooper())
    private val animationRunnable = object : Runnable {
        override fun run() {
            phase += 10f
            invalidate()
            handler.postDelayed(this, 30)
        }
    }

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
        paint.isAntiAlias = true
        handler.post(animationRunnable)
    }

    fun setBorderColor(newColor: Int) {
        this.color = newColor
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val pathEffect = DashPathEffect(floatArrayOf(40f, 20f), phase)
        paint.pathEffect = pathEffect
        paint.color = color

        val rect = RectF(
            strokeWidth / 2,
            strokeWidth / 2,
            width - strokeWidth / 2,
            height - strokeWidth / 2
        )
        canvas.drawRect(rect, paint)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        handler.removeCallbacks(animationRunnable) // Avoid leaks
    }
}



