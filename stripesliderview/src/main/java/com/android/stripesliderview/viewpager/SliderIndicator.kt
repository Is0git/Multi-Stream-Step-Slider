package com.android.stripesliderview.viewpager

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import com.android.stripesliderview.R
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class SliderIndicator : View {

    var indicatorsSize = 3

    var circleRadius = 25f

    val circleDiameter = circleRadius * 2

    val indicatorsMargin = 50f

    val selectedCircleRadius = 30f

    var selectedPosition = 1



    var animPosition = 1f
        set(value) {
            field = value
            invalidate()
        }

    lateinit var selectedOvalPaint: Paint

    val selectedCircleDiameter = selectedCircleRadius * 2

    lateinit var selectedIndicatorPaint: Paint

    lateinit var defaultCirclePaint : Paint

    constructor(context: Context?) : super(context) {init(context)}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {init(context)}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {init(context)}


    fun init(context: Context?, attrs: AttributeSet? = null) {
        defaultCirclePaint = Paint().apply {
            shader = LinearGradient(0f, 0f, 0f, selectedCircleDiameter, resources.getColor(
                R.color.colorSurface
            ), resources.getColor(R.color.colorOnSurface), Shader.TileMode.CLAMP)
        }

        selectedIndicatorPaint = Paint().apply {
            shader = LinearGradient(0f, 0f, 0f, selectedCircleDiameter, resources.getColor(
                R.color.colorPrimary
            ), resources.getColor(R.color.colorOnSecondaryVariant), Shader.TileMode.CLAMP)
        }

        selectedOvalPaint = Paint().apply {
            color = Color.BLACK
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val desiredWidth = (circleDiameter * indicatorsSize)+ paddingStart + paddingEnd + (indicatorsMargin * indicatorsSize - 1)
        val desiredHeight = (selectedCircleRadius * 2) + paddingTop + paddingBottom
        val width = resolveSize(desiredWidth.toInt(), widthMeasureSpec)
        val height = resolveSize(desiredHeight.toInt(), heightMeasureSpec)
        Log.d("TEST", "CANVAS: ${desiredWidth}, NORMAL: $width PADDING START: $paddingStart PADDAINGEND: $paddingEnd")
        setMeasuredDimension(width, height)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(indicatorsSize == 0) return
        val midY = height / 2f
        Log.d("TEST", "CANVAS: ${canvas?.width}, NORMAL: $width MEASURED: $measuredWidth")

        canvas?.drawCircle(circleRadius + paddingStart, midY, circleRadius, defaultCirclePaint)

        val midIndicatorPadding = circleRadius + paddingStart
        for(a in 1 until indicatorsSize) {
                canvas?.drawCircle((a * circleDiameter) + midIndicatorPadding + (indicatorsMargin *a), midY, circleRadius, defaultCirclePaint)

        }
        val ovalLenght = indicatorsMargin + circleDiameter
        val gap = (indicatorsMargin + circleDiameter) * selectedPosition
        val startAnimPos = if (animPosition == 0f) 0f else (indicatorsMargin + circleDiameter) * (1 - animPosition.absoluteValue)
        Log.d("GAP", "VALIUE: ${gap * 1 - animPosition.absoluteValue} ANIMP: ${animPosition.absoluteValue} POSITION: $selectedPosition")
        canvas?.drawOval(gap + startAnimPos,0f, gap + circleDiameter +startAnimPos, 50f, selectedIndicatorPaint)
    }
}

fun convertDpToPixel(dp: Float): Int {
    val metrics: DisplayMetrics = Resources.getSystem().displayMetrics
    val px = dp * (metrics.densityDpi / 160f)
    return px.roundToInt()
}

fun convertPixelsToDp(px: Float, context: Context): Float {
    return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}