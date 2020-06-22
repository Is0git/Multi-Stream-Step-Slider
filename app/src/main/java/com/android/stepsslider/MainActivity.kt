package com.android.stepsslider

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.stripesliderview.listeners.OnProgressButtonListener
import com.android.stripesliderview.slider.SlideLayout
import com.android.stripesliderview.viewpager.PageData
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    lateinit var slideLayout: SlideLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        slideLayout = findViewById(R.id.SlideLayout)


        val view = findViewById<MaterialButton>(R.id.skip_button)
        val pageOne = PageData.Builder()
            .setPageButtonText("SIGN IN")
            .setTitleText("TWITCH")
            .setLogoDrawableId(R.drawable.ic_twitch_logo)
            .setCircleDrawableId(R.drawable.ic_circle)
            .setUnderCircleDrawableId(R.drawable.ic_lines)
            .setLogoWidthRatio(1f)
            .setHeightRatio(0.70f)
            .setLogoOffSetRatio(0.50f)
            .setState(PageData.ProgressButtonState.STARTED)
            .setOnSyncButtonClickListener { Log.d("AUTH", "TWITCH CLICK") }
            .build()

        val pageTwo = PageData.Builder()
            .setPageButtonText("SIGN IN")-
            .setTitleText("MIXER")
            .setLogoDrawableId(R.drawable.ic_mixer_logo)
            .setCircleDrawableId(R.drawable.ic_circle)
            .setUnderCircleDrawableId(R.drawable.ic_lines)
            .setLogoWidthRatio(0.80f)
            .setHeightRatio(0.30f)
            .setLogoOffSetRatio(0.90f)
            .setState(PageData.ProgressButtonState.STARTED)
            .setOnSyncButtonClickListener { Log.d("AUTH", "MIXER CLICK") }
            .build()

        val pageList = listOf(pageOne, pageTwo)
        slideLayout.viewPagerAdapter.addPages(pageList)


            slideLayout.onSkipButtonClick {        slideLayout.getPage(0).state = PageData.ProgressButtonState.COMPLETED
                slideLayout.notifyItemChanged(0) }
//        view.setOnClickListener { slideLayout.showSkipTapTarget(this) }
    }

}
