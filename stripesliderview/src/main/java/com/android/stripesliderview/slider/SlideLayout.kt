package com.android.stripesliderview.slider

import android.animation.AnimatorSet
import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import com.android.stripesliderview.R
import com.android.stripesliderview.viewpager.ViewPagerAdapter
import com.google.android.material.button.MaterialButton

class SlideLayout : ConstraintLayout {
    lateinit var sliderBackgroundView: SliderBackgroundView
    lateinit var nextButton: MaterialButton
    lateinit var indicator: SliderIndicator
    lateinit var viewPager: ViewPager2
    lateinit var viewPagerAdapter: ViewPagerAdapter

    var animatorSet: AnimatorSet? = null

    var itemsCount = 0

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


    private fun init(context: Context?, attrs: AttributeSet? = null) {
        if (attrs != null) {
            context?.obtainStyledAttributes(attrs, R.styleable.SlideLayout)?.apply {
                itemsCount = getInteger(R.styleable.SlideLayout_slidePagesCount, 0)
                recycle()
            }
        }


        sliderBackgroundView = SliderBackgroundView(
            context
        ).apply {
            this.id = R.id.background_id
        }

        indicator = SliderIndicator(
            context, itemsCount
        ).apply {
            id = R.id.indicators
        }

        nextButton = MaterialButton(
            context!!, null,
            R.attr.borderlessButtonStyle
        ).apply {
            text = resources.getString(R.string.next)
            typeface = ResourcesCompat.getFont(
                context,
                R.font.ostrich
            )
            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            this.textSize = 25f
            id = R.id.next_button
            setOnClickListener {
                viewPager.apply {
                    setCurrentItem(currentItem + 1, true)
                }
            }
        }

        viewPager = ViewPager2(context).apply {
            id = R.id.viewPagerView
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {

                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    indicator.selectedPosition = position
                }

                override fun onPageSelected(position: Int) {


                }
            })
            viewPagerAdapter = ViewPagerAdapter(itemsCount)
                .also { adapter = it }
            setPageTransformer { page, position ->
                page.scaleY = 1 - (0.90f * kotlin.math.abs(position))
                page.scaleX = 1 - (0.90f * kotlin.math.abs(position))
                indicator.animPosition = position
            }
        }


        this.setConstraintSet(ConstraintSet().apply {
            clone(
                context,
                R.layout.layout_place_holder
            )
        })

        addView(sliderBackgroundView)

        addView(indicator)

        addView(nextButton)

        addView(viewPager)

        animatorSet = AnimatorSet()

    }


}

fun dpToPx(dp: Float, resources: Resources) = (dp / resources.displayMetrics.density).toInt()