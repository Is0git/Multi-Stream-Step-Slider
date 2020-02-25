package com.android.stripesliderview

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import com.android.eminogoview.EminogoView
import com.google.android.material.button.MaterialButton

class SliderPageLayout : ConstraintLayout {

    lateinit var eminogoView: EminogoView
    lateinit var signUpText: TextView
    lateinit var signUpButton: MaterialButton

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




        eminogoView = EminogoView(context).apply {
            id = R.id.eminogo_view_id
        }
        signUpText = TextView(context).apply {
            this.text = "SIGN UP WITH TWITCH"
            this.textSize = 50f
            this.textAlignment = View.TEXT_ALIGNMENT_CENTER
            this.id = R.id.sign_up_text
            typeface = ResourcesCompat.getFont(context!!, R.font.ostrich)
        }

        signUpButton = MaterialButton(context!!).apply {
            id = R.id.signUpButton
            text = "SIGN IN"
            typeface = ResourcesCompat.getFont(context, R.font.myriad_pro)
            this.backgroundTintList = ColorStateList.valueOf(ResourcesCompat.getColor(resources,R.color.colorPrimary, null))
            this.cornerRadius = 80
            this.textSize = 25f
        }



        this.setConstraintSet(ConstraintSet().apply {
            clone(context, R.layout.place_holder)
        })

        addView(eminogoView)

        addView(signUpText)

        addView(signUpButton)
    }
}