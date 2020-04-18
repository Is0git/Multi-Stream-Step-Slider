package com.android.stepsslider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.stripesliderview.slider.SlideLayout
import com.android.stripesliderview.viewpager.PageData
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    lateinit var slideLayout: SlideLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       slideLayout = findViewById<SlideLayout>(R.id.SlideLayout)
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
            ),
            PageData(
                "DO SOMETHING",
                "HELLO",
                R.drawable.ic_mixer_logo,
                R.drawable.ic_circle,
                R.drawable.ic_lines,
                0.80f,
                0.30f,
                0.90f
            )
        )

        slideLayout.viewPagerAdapter.addPages(listPage)
        view.setOnClickListener {  slideLayout.showSkipTapTarget(this) }
    }
}
