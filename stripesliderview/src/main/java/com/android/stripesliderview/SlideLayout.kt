package com.android.stripesliderview

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import com.android.eminogoview.EminogoView
import com.google.android.material.button.MaterialButton

class SlideLayout : ConstraintLayout {
    lateinit var sliderBackgroundView: SliderBackgroundView
    lateinit var nextButton: MaterialButton
    lateinit var indicator: SliderIndicator
    lateinit var viewPager: ViewPager2
    lateinit var viewPagerAdapter: ViewPagerAdapter

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


    private fun init(context: Context?) {
        sliderBackgroundView = SliderBackgroundView(context).apply {
            this.id = R.id.background_id
        }

        indicator = SliderIndicator(context).apply {
            id = R.id.indicators
        }

        nextButton = MaterialButton(context!!, null, R.attr.borderlessButtonStyle).apply {
            text = "NEXT"
            typeface = ResourcesCompat.getFont(context, R.font.ostrich)
            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            this.textSize = 25f
            id = R.id.next_button
        }

        viewPager = ViewPager2(context).apply {
            id = R.id.viewPagerView
            viewPagerAdapter = ViewPagerAdapter().also { adapter = it }
        }

        this.setConstraintSet(ConstraintSet().apply {
            clone(context, R.layout.layout_place_holder)
        })


        addView(sliderBackgroundView)

        addView(indicator)

        addView(nextButton)

        addView(viewPager)


    }


}

fun  dpToPx(dp: Float, resources: Resources) = (dp / resources.displayMetrics.density).toInt()