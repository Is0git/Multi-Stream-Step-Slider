package com.android.stripesliderview.viewpager

import android.view.View

data class PageData(
    var pageButtonText: String,
    var mainText: String,
    var logoDrawableId: Int,
    var circleDrawableId: Int,
    var underCircleDrawableId: Int,
    var logoWidthRatio: Float,
    var logoHeightRatio: Float,
    var logoOffSetRatio: Float,
    var onSyncClick: () -> Unit) {
}