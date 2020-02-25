package com.android.stripesliderview

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.core.view.marginStart
import kotlin.math.roundToInt

class SliderIndicator : View {

    var indicatorsSize = 5

    var circleRadius = 25f

    val circleDiameter = circleRadius * 2

    val indicatorsMargin = 50f

    val selectedCircleRadius = 75f


    lateinit var defaultCirclePaint : Paint

    constructor(context: Context?) : super(context) {init(context)}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {init(context)}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {init(context)}

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {init(context)}

    fun init(context: Context?, attrs: AttributeSet? = null) {
        defaultCirclePaint = Paint().apply {
            color = Color.BLACK
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val desiredWidth = (circleDiameter * indicatorsSize)+ paddingStart + paddingEnd + (indicatorsMargin * indicatorsSize - 1)
        val desiredHeight = selectedCircleRadius + paddingTop + paddingBottom
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