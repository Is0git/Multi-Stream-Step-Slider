package com.android.stripesliderview.viewpager

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class ZoomPagerTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
    }
}