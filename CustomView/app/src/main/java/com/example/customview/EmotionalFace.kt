package com.example.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class EmotionalFace(context : Context,attrs : AttributeSet) : View(context,attrs) {
    private val paint = Paint()
    private var size = 0

     var faceColor = DEFAULT_FACE_COLOR
        set(value) {
            field = value
            invalidate()
        }
    private var eyesColor = DEFAULT_EYES_COLOR
    private var mouthColor = DEFAULT_MOUTH_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidth = DEFAULT_BORDER_WIDTH

    companion object {
        private const val DEFAULT_FACE_COLOR = Color.YELLOW
        private const val DEFAULT_EYES_COLOR = Color.BLACK
        private const val DEFAULT_MOUTH_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 4.0f

        const val HAPPY = 0L
        const val SAD = 1L
    }

    var happinessState = HAPPY
        set(state) {
            field = state
            invalidate()
        }
    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {

        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.EmotionalFace,
            0, 0)

        happinessState = typedArray.getInt(R.styleable.EmotionalFace_state, HAPPY.toInt()).toLong()
        faceColor = typedArray.getColor(R.styleable.EmotionalFace_faceColor, DEFAULT_FACE_COLOR)
        eyesColor = typedArray.getColor(R.styleable.EmotionalFace_eyesColor, DEFAULT_EYES_COLOR)
        mouthColor = typedArray.getColor(R.styleable.EmotionalFace_mouthColor, DEFAULT_MOUTH_COLOR)
        borderColor = typedArray.getColor(R.styleable.EmotionalFace_borderColor,
            DEFAULT_BORDER_COLOR)
        borderWidth = typedArray.getDimension(R.styleable.EmotionalFace_borderWidth,
            DEFAULT_BORDER_WIDTH)


        typedArray.recycle()
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)
    }


    private fun drawEyes(canvas: Canvas?) {

        paint.color = eyesColor
        paint.style = Paint.Style.FILL
        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)
        canvas!!.drawOval(leftEyeRect, paint)
        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)
        canvas.drawOval(rightEyeRect, paint)

    }

    private fun drawMouth(canvas: Canvas?) {
        val mouthPath = Path()
        mouthPath.reset()
        mouthPath.moveTo(size * 0.22f, size * 0.7f)
        if (happinessState == HAPPY) {
            mouthPath.quadTo(size * 0.5f, size * 0.80f, size * 0.78f, size * 0.7f)
            mouthPath.quadTo(size * 0.5f, size * 0.90f, size * 0.22f, size * 0.7f)
        } else {
            mouthPath.quadTo(size * 0.5f, size * 0.50f, size * 0.78f, size * 0.7f)
            mouthPath.quadTo(size * 0.5f, size * 0.60f, size * 0.22f, size * 0.7f)
        }

        paint.color = mouthColor
        paint.style = Paint.Style.FILL

        canvas!!.drawPath(mouthPath, paint)
    }

    private fun drawFaceBackground(canvas: Canvas?) {
        paint.color = faceColor
        paint.style = Paint.Style.FILL

        val radius = size / 2f
        canvas!!.drawCircle(size / 2f, size / 2f, radius, paint)
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth
        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = Math.min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }
}