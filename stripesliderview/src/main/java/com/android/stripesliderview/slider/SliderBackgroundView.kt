package com.android.stripesliderview.slider

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.android.stripesliderview.R

class SliderBackgroundView : View {
    lateinit var bgShapePath: Path
    lateinit var bgShapePaint: Paint
    lateinit var underBgPaint: Paint

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }


    fun init(context: Context?, attrs: AttributeSet? = null) {
        bgShapePath = Path()

        underBgPaint = Paint()

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        underBgPaint.shader = LinearGradient(
            0f,
            0f,
            0f,
            h.toFloat(),
            ResourcesCompat.getColor(resources, R.color.colorSurface, null),
            ResourcesCompat.getColor(resources, R.color.colorOnSurface, null),
            Shader.TileMode.CLAMP)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        bgShapePath.apply {
            val width = this@SliderBackgroundView.width.toFloat()
            val height = this@SliderBackgroundView.height.toFloat()
            lineTo(0f, height * 0.87f)
            cubicTo(
                width * 0.20f,
                height * 1.0f,
                width * 0.50f,
                height * 1.04f,
                width,
                height * 0.85f
            )
            lineTo(width, 0f)
            close()
        }


        canvas?.drawPath(bgShapePath, underBgPaint)

    }
}