package com.android.stepsslider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.stripesliderview.slider.SlideLayout
import com.android.stripesliderview.viewpager.PageData

class MainActivity : AppCompatActivity() {
    lateinit var slideLayout: SlideLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = findViewById<SlideLayout>(R.id.SlideLayout)

        val listPage = listOf(
            PageData(
                "DO SOMETHING",
                "HELLO",
                R.drawable.ic_twitch_logo,
                R.drawable.ic_circle,
                R.drawable.ic_lines
            ),
            PageData(
                "DO SOMETHING",
                "HELLO",
                R.drawable.ic_mixer_logo,
                R.drawable.ic_circle,
                R.drawable.ic_lines
            )
        )

        view.viewPagerAdapter.addPages(listPage)
    }
}
