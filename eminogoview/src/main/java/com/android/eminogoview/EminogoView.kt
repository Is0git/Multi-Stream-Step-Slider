package com.android.eminogoview

import android.content.Context
import android.content.res.Configuration
import android.graphics.*
import android.graphics.drawable.VectorDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.android.getBitmap


class EminogoView : View {

    lateinit var circleShape: Path
    lateinit var circlePaint: Paint
    lateinit var circleBitMap: Bitmap
    lateinit var circleDrawable: VectorDrawable
    lateinit var lineDrawable: VectorDrawable
    lateinit var twichDrawable: VectorDrawable
    lateinit var lineBitmap: Bitmap
    lateinit var twitchBitmap: Bitmap
    lateinit var twitchPaint: Paint
    lateinit var circleRectF: RectF
    lateinit var underCirclePaint: Paint

    val circleSizeRatio = 0.75f

    val logoHeightSizeRatio = 0.70f
    val logoWidthSizeRatio = 1f

    constructor(context: Context?) : super(context) {
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

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun init(context: Context?) {
        underCirclePaint = Paint().apply {
            color = Color.WHITE
        }

        circlePaint = Paint().apply {

        }
        twitchPaint = Paint().apply {
            strokeWidth = 20f
            color = Color.WHITE
            style = Paint.Style.STROKE
        }
        circleDrawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_circle, null) as VectorDrawable
        lineDrawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_image, null) as VectorDrawable
        twichDrawable = ResourcesCompat.getDrawable(resources,R.drawable.ic_twitch_logo, null) as VectorDrawable


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = (MeasureSpec.getSize(widthMeasureSpec) * 0.80f).toInt()
        val heightMode = (MeasureSpec.getSize(heightMeasureSpec) * 0.80f).toInt()
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

//        var width = if (widthMode == MeasureSpec.AT_MOST) widthSize else widthSize
//
//        var height = if (heightMode == MeasureSpec.AT_MOST) widthSize else heightSize

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setMeasuredDimension(heightSize, heightSize)
        } else {
            setMeasuredDimension(widthSize, widthSize)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (::circleBitMap.isInitialized) return
        else {
            circleBitMap = getBitmap(circleDrawable, (width *circleSizeRatio).toInt(), (height *circleSizeRatio).toInt())!!
            lineBitmap = getBitmap(lineDrawable, width, height)!!
            twitchBitmap = getBitmap(twichDrawable, (width *logoWidthSizeRatio).toInt(), (height *logoHeightSizeRatio).toInt())!!
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val midX = width / 2f
        val midY = height / 2f

        val circleOffSetX = midX - (width *circleSizeRatio) /2f
        val circleOffSetY = midY - (height *circleSizeRatio) /2f

        val logoOffSetX = midX - (width *logoWidthSizeRatio) /2f
        val logoOffSetY = midY - (height *logoHeightSizeRatio) /2f

        canvas?.drawArc(0f, 0f, width.toFloat() * 1f, height.toFloat() * 1f, -45f, 180f, true, underCirclePaint)
        canvas?.drawBitmap(lineBitmap, 0f, 0f, circlePaint)
        canvas?.drawBitmap(circleBitMap, circleOffSetX.toFloat(), circleOffSetY.toFloat(), circlePaint)
        canvas?.drawBitmap(twitchBitmap, logoOffSetX.toFloat(), logoOffSetY * 0.50f, twitchPaint)



    }
}