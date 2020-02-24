package com.android.stripesliderview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginBottom
import com.android.eminogoview.EminogoView
import com.google.android.material.button.MaterialButton

class SlideLayout : ConstraintLayout {
    lateinit var eminogoView: EminogoView
    lateinit var sliderBackgroundView: SliderBackgroundView
    lateinit var textView: TextView
    lateinit var signUpButton: MaterialButton
    lateinit var nextButton: MaterialButton

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

    fun init(context: Context?) {


        sliderBackgroundView = SliderBackgroundView(context).apply {
            this.id = R.id.background_id
            layoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
                    this.bottomToBottom = LayoutParams.PARENT_ID
                    this.topToTop = LayoutParams.PARENT_ID
                    this.rightToRight = LayoutParams.PARENT_ID
                    this.leftToLeft = LayoutParams.PARENT_ID
                    this.bottomMargin = 200
                }
        }

        eminogoView = EminogoView(context).apply {
            id = R.id.eminogo_view_id
            layoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                    this.topToTop = LayoutParams.PARENT_ID
                    this.rightToRight = LayoutParams.PARENT_ID
                    this.leftToLeft = LayoutParams.PARENT_ID
                    topMargin = 150
                    marginStart = 50
                    marginEnd = 50
                }
        }
        textView = TextView(context).apply {
            this.text = "SIGN UP WITH TWITCH"
            this.textSize = 50f
            this.textAlignment = View.TEXT_ALIGNMENT_CENTER
            this.id = R.id.sign_up_text
            typeface = ResourcesCompat.getFont(context!!, R.font.ostrich)
            layoutParams =
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    this.topToBottom = R.id.eminogo_view_id
                    this.rightToRight = LayoutParams.PARENT_ID
                    this.leftToLeft = LayoutParams.PARENT_ID
                    this.topMargin = 220
                }
        }

        signUpButton = MaterialButton(context!!).apply {
            text = "SIGN IN"
            typeface = ResourcesCompat.getFont(context, R.font.myriad_pro)
            this.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorPrimary))
            this.cornerRadius = 80
            this.textSize = 25f
            this.elevation = 100f
            layoutParams =
                LayoutParams(LayoutParams.MATCH_CONSTRAINT, 285).apply {
                    this.topToBottom = R.id.sign_up_text
                    this.rightToRight = LayoutParams.PARENT_ID
                    this.leftToLeft = LayoutParams.PARENT_ID
                    this.bottomToBottom = R.id.background_id
                    marginStart = 200
                    marginEnd = 200
                    verticalBias = 0.4f
                }
        }

        nextButton = MaterialButton(context,null, R.attr.borderlessButtonStyle).apply {
            text = "NEXT"
            typeface = ResourcesCompat.getFont(context, R.font.ostrich)
            this.textSize = 25f
            layoutParams =
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    this.topToBottom = R.id.background_id
                    this.rightToRight = LayoutParams.PARENT_ID
                    this.leftToLeft = LayoutParams.PARENT_ID
                    this.bottomToBottom = LayoutParams.PARENT_ID
                    marginEnd = 100
                    bottomMargin = 100
                    horizontalBias = 1f
                    verticalBias = 1f
                }
        }
        addView(sliderBackgroundView)

        addView(eminogoView)

        addView(textView)

        addView(signUpButton)

        addView(nextButton)
    }


}