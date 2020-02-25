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
import com.android.eminogoview.EminogoView
import com.google.android.material.button.MaterialButton

class SlideLayout : ConstraintLayout {
    lateinit var eminogoView: EminogoView
    lateinit var sliderBackgroundView: SliderBackgroundView
    lateinit var signUpText: TextView
    lateinit var signUpButton: MaterialButton
    lateinit var nextButton: MaterialButton
    lateinit var indicator: SliderIndicator

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
            layoutParams =
                LayoutParams(LayoutParams.MATCH_CONSTRAINT, LayoutParams.MATCH_CONSTRAINT)
        }

        eminogoView = EminogoView(context).apply {
            id = R.id.eminogo_view_id
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }
        signUpText = TextView(context).apply {
            this.text = "SIGN UP WITH TWITCH"
            this.textSize = 50f
            this.textAlignment = View.TEXT_ALIGNMENT_CENTER
            this.id = R.id.sign_up_text
            typeface = ResourcesCompat.getFont(context!!, R.font.ostrich)
            layoutParams =
                LayoutParams(LayoutParams.MATCH_CONSTRAINT, 100)
        }

        signUpButton = MaterialButton(context!!).apply {
            id = R.id.signUpButton
            text = "SIGN IN"
            typeface = ResourcesCompat.getFont(context, R.font.myriad_pro)
            this.backgroundTintList =
                ColorStateList.valueOf(ResourcesCompat.getColor(resources,R.color.colorPrimary, null))
            this.cornerRadius = 80
            this.textSize = 25f
            layoutParams =
                LayoutParams(LayoutParams.MATCH_CONSTRAINT,100)
        }


        indicator = SliderIndicator(context).apply {
            id = R.id.indicators
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }

        nextButton = MaterialButton(context, null, R.attr.borderlessButtonStyle).apply {
            text = "NEXT"
            typeface = ResourcesCompat.getFont(context, R.font.ostrich)
            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            this.textSize = 25f
            id = R.id.next_button
            layoutParams = LayoutParams(LayoutParams.MATCH_CONSTRAINT, LayoutParams.WRAP_CONTENT)
        }

        this.setConstraintSet(ConstraintSet().apply {
            clone(context, R.layout.place_holder)
        })


        addView(sliderBackgroundView)

        addView(eminogoView)

        addView(signUpText)

        addView(signUpButton)

        addView(indicator)

        addView(nextButton)


    }


}

fun  dpToPx(dp: Float, resources: Resources) = (dp / resources.displayMetrics.density).toInt()