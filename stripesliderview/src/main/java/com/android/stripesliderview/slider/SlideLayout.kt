package com.android.stripesliderview.slider

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.TextViewCompat
import androidx.viewpager2.widget.ViewPager2
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.android.stripesliderview.R
import com.android.stripesliderview.listeners.OnProgressButtonListener
import com.android.stripesliderview.viewpager.PageData
import com.android.stripesliderview.viewpager.ViewPagerAdapter
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView
import com.google.android.material.button.MaterialButton
import java.util.*


class SlideLayout : ConstraintLayout {
    lateinit var sliderBackgroundView: SliderBackgroundView
    lateinit var nextButton: MaterialButton
    lateinit var indicator: SliderIndicator
    lateinit var viewPager: ViewPager2
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var skipButton: MaterialButton


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

        background = ResourcesCompat.getDrawable(resources, R.drawable.slider_bg_gradient, null)

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
            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        }

        viewPager = ViewPager2(context).apply {
            id = R.id.view_pager_view
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
            viewPagerAdapter = ViewPagerAdapter(itemsCount).also { it ->
                adapter = it
                it.onProgressButtonListener = object : OnProgressButtonListener {
                    override fun onClick(view: CircularProgressButton) {
                        viewPager.currentItem.also { position ->
                            getPage(position).onSyncClick?.invoke()
                        }
                    }

                }
            }
            setPageTransformer { page, position ->
                Log.d("TEST", position.toString())
                page.scaleY = 1 - (0.90f * kotlin.math.abs(position))
                page.scaleX = 1 - (0.90f * kotlin.math.abs(position))
                indicator.animPosition = position
            }
        }


        skipButton = MaterialButton(context, null, R.attr.borderlessButtonStyle).apply {
            TextViewCompat.setTextAppearance(this, R.style.TextAppearance_MaterialComponents_Body1)
            text = context.getString(R.string.skip)
            this.setTypeface(typeface, Typeface.BOLD)
            id = R.id.skip_button
        }

        this.setConstraintSet(ConstraintSet().apply {
            clone(
                context,
                R.layout.layout_place_holder
            )
        })

        addView(sliderBackgroundView)
        addView(indicator)
        addView(viewPager)
        addView(skipButton)
        addView(nextButton)

    }

    fun showSkipTapTarget(activity: Activity) {
        TapTargetView.showFor(activity,  // `this` is an Activity
            TapTarget.forView(
                nextButton,
                "Welcome!",
                "We highly recommend to connect your stream accounts in order to get enjoy all the features and have your data synchronized"
            ) // All options below are optional
                .outerCircleAlpha(0.96f) // Specify the alpha amount for the outer circle
                .titleTextSize(20) // Specify the size (in sp) of the title text
                .descriptionTextSize(18)
                .outerCircleColor(R.color.colorPrimary)
                .titleTextColor(R.color.colorOnSurface) // Specify the color of the title text
                .descriptionTextSize(10) // Specify the size (in sp) of the description text
                .descriptionTextColor(R.color.colorOnSurface) // Specify the color of the description text
                .textColor(R.color.colorOnSurface) // Specify a color for both the title and description text
                .transparentTarget(true)
                .textTypeface(Typeface.SANS_SERIF) // Specify a typeface for the text
                .targetRadius(60),  // Specify the target radius (in dp)
            object : TapTargetView.Listener() {
                // The listener can listen for regular clicks, long clicks or cancels
                override fun onTargetClick(view: TapTargetView) {
                    super.onTargetClick(view) // This call is optional

                }
            })
    }

    fun onSkipButtonClick(onClick: (view: View) -> Unit) {
        skipButton.setOnClickListener { onClick(it) }
    }

    fun addPages(pageData: List<PageData>) {
        viewPagerAdapter.addPages(pageData)
    }

    fun getCurrentPageData(): PageData {
        return viewPagerAdapter.pageDataList[viewPager.currentItem]
    }

    fun getPage(position: Int): PageData {
       return viewPagerAdapter.pageDataList[position]
    }

    private fun setOnProgressButtonListener(listener: OnProgressButtonListener) {
        viewPagerAdapter.onProgressButtonListener = listener
    }


    fun notifyItemChanged(position: Int) {
        viewPagerAdapter.notifyItemChanged(position)
    }
}

fun dpToPx(dp: Float, resources: Resources) = (dp / resources.displayMetrics.density).toInt()