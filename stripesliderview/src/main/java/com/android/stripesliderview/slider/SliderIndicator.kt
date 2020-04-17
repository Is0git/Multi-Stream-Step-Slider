package com.android.stripesliderview.slider

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.android.stripesliderview.R
import kotlin.math.absoluteValue

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

    lateinit var defaultCirclePaint: Paint

    constructor(context: Context?, itemsCount: Int = 0) : super(context) {
        this.indicatorsSize = itemsCount
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }


    private fun init(context: Context?, attrs: AttributeSet? = null) {
        defaultCirclePaint = Paint().apply {
            shader = LinearGradient(
                0f, 0f, 0f, selectedCircleDiameter, resources.getColor(
                    R.color.colorSurface
                ), resources.getColor(R.color.colorOnSurface), Shader.TileMode.CLAMP
            )
        }

        selectedIndicatorPaint = Paint().apply {
            shader = LinearGradient(
                0f, 0f, 0f, selectedCircleDiameter, resources.getColor(
                    R.color.colorPrimary
                ), resources.getColor(R.color.colorOnSecondaryVariant), Shader.TileMode.CLAMP
            )
        }

        selectedOvalPaint = Paint().apply {
            color = Color.BLACK
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val desiredWidth =
            (circleDiameter * indicatorsSize) + paddingStart + paddingEnd + (indicatorsMargin * indicatorsSize - 1)
        val desiredHeight = (selectedCircleRadius * 2) + paddingTop + paddingBottom
        val width = resolveSize(desiredWidth.toInt(), widthMeasureSpec)
        val height = resolveSize(desiredHeight.toInt(), heightMeasureSpec)
        Log.d(
            "TEST",
            "CANVAS: ${desiredWidth}, NORMAL: $width PADDING START: $paddingStart PADDAINGEND: $paddingEnd"
        )
        setMeasuredDimension(width, height)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (indicatorsSize == 0) return
        val midY = height / 2f

        canvas?.drawCircle(circleRadius + paddingStart, midY, circleRadius, defaultCirclePaint)

        val midIndicatorPadding = circleRadius + paddingStart
        for (a in 1 until indicatorsSize) {
            canvas?.drawCircle(
                (a * circleDiameter) + midIndicatorPadding + (indicatorsMargin * a),
                midY,
                circleRadius,
                defaultCirclePaint
            )

        }
        val ovalLength = indicatorsMargin + circleDiameter
        val gap = (indicatorsMargin + circleDiameter) * selectedPosition

        val startXAnimPos: Float

        val startYAnimPos: Float

        val endYPosition: Float

        if (animPosition == 0f) {
            startXAnimPos = 0f
            startYAnimPos = 0f
            endYPosition = circleDiameter
        } else {
            val positionInMotion = 1 - animPosition.absoluteValue

            startXAnimPos = ovalLength * positionInMotion
            if (animPosition.absoluteValue < 0.75f &&  animPosition.absoluteValue > 0.25f) {
                startYAnimPos = circleDiameter * 0.25f
                endYPosition = circleDiameter * 0.75f
            }
            else if (animPosition.absoluteValue > 0.75f) {
                endYPosition = circleDiameter * animPosition.absoluteValue
                startYAnimPos = circleDiameter * positionInMotion

            } else  {
                endYPosition = circleDiameter * positionInMotion
                startYAnimPos = circleDiameter * animPosition.absoluteValue
            }

        }


        canvas?.drawOval(
            gap + startXAnimPos,
            startYAnimPos,
            gap + circleDiameter + startXAnimPos,
            endYPosition,
            selectedIndicatorPaint
        )
    }
}
