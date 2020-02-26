package com.android.stripesliderview.slider

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.android.stripesliderview.R

class SliderBackgroundView : View {
    lateinit var bgShapePath: Path
    lateinit var bgShapePaint: Paint
    lateinit var underBgPaint: Paint

    constructor(context: Context?) : super(context){init(context)}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {init(context, attrs)}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {init(context, attrs)}



    fun init(context: Context?, attrs: AttributeSet? = null) {
        bgShapePath = Path()

        underBgPaint = Paint().apply {  color = Color.parseColor("#E7E7E7")
        }
        val bgBitmap = BitmapFactory.decodeResource(context?.resources,
            R.drawable.stripe_slider_bg)

        bgShapePaint = Paint().apply {
            color = Color.BLACK
            isDither = true
            alpha = 200
            style = Paint.Style.FILL_AND_STROKE
            isAntiAlias = true
            strokeWidth = 50f
            shader = BitmapShader(bgBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        bgShapePath.apply {
            val width = this@SliderBackgroundView.width.toFloat()
            val height = this@SliderBackgroundView.height.toFloat()
            lineTo(0f, height*0.87f)
            cubicTo(width * 0.20f, height * 1.0f, width * 0.50f, height * 1.04f, width, height  * 0.85f)
            lineTo(width, 0f)
            close()
        }
        canvas?.drawPath(bgShapePath, underBgPaint)
        canvas?.drawPath(bgShapePath, bgShapePaint)
    }
}