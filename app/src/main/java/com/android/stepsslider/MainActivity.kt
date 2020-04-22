package com.android.stepsslider

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.stripesliderview.listeners.OnProgressButtonListener
import com.android.stripesliderview.slider.SlideLayout
import com.android.stripesliderview.viewpager.PageData
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity(), OnProgressButtonListener{
    lateinit var slideLayout: SlideLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       slideLayout = findViewById(R.id.SlideLayout)
        slideLayout.setOnProgressButtonListener(this)
        val view = findViewById<MaterialButton>(R.id.skip_button)
        val listPage = listOf(
            PageData(
                "DO SOMETHING",
                "HELLO",
                R.drawable.ic_twitch_logo,
                R.drawable.ic_circle,
                R.drawable.ic_lines,
                1f,
                0.70f,
                0.50f
            ) {Log.d("CLICK", "TWTITCH CLICK")},
            PageData(
                "DO SOMETHING",
                "HELLO",
                R.drawable.ic_mixer_logo,
                R.drawable.ic_circle,
                R.drawable.ic_lines,
                0.80f,
                0.30f,
                0.90f
            ) {Log.d("CLICK", "MIXER CLICK")}
        )

        slideLayout.viewPagerAdapter.addPages(listPage)
        view.setOnClickListener {  slideLayout.showSkipTapTarget(this) }
    }

    override fun onClick(view: br.com.simplepass.loadingbutton.customViews.CircularProgressButton) {
        slideLayout.getCurrentPageData().onSyncClick()
    }
}
