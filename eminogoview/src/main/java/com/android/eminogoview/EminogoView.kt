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
import androidx.core.graphics.alpha
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
    lateinit var linesPaint: Paint
    var circleAlpha = 0
    val circleSizeRatio = 0.75f

    var twichLogoAlpha = 0
    val logoHeightSizeRatio = 0.70f
    val logoWidthSizeRatio = 1f

    var linesAlpha = 0
    var linesX = 0f
    var linesY = 0f

    var startAngle = -45f
    var sweepAngle = 180f

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
            alpha = circleAlpha

        }
        twitchPaint = Paint().apply {
            alpha = twichLogoAlpha

        }

        linesPaint = Paint().apply {
            alpha = linesAlpha
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

        linesPaint.alpha = linesAlpha
        twitchPaint.alpha = twichLogoAlpha
        circlePaint.alpha = circleAlpha

        canvas?.drawArc(0f, 0f, width.toFloat() * 1f, height.toFloat() * 1f, startAngle, sweepAngle, true, underCirclePaint)
        canvas?.drawBitmap(lineBitmap, linesX, linesY, linesPaint)
        canvas?.drawBitmap(circleBitMap, circleOffSetX, circleOffSetY, circlePaint)
        canvas?.drawBitmap(twitchBitmap, logoOffSetX, logoOffSetY * 0.50f, twitchPaint)

    }
}