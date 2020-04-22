package com.android.stripesliderview.viewpager

import android.view.View
import br.com.simplepass.loadingbutton.presentation.State
import java.lang.IllegalStateException
import java.lang.NullPointerException

data class PageData(
    var pageButtonText: String,
    var mainText: String,
    var logoDrawableId: Int,
    var circleDrawableId: Int,
    var underCircleDrawableId: Int,
    var logoWidthRatio: Float,
    var logoHeightRatio: Float,
    var logoOffSetRatio: Float,
    var state: ProgressButtonState = ProgressButtonState.IDLE,
    var onSyncClick: (() -> Unit)?
) {

    class Builder {
        private var pageButtonText: String? = null
        private var mainText: String? = null
        private var logoDrawableId: Int = 0
        private var circleDrawableId: Int = 0
        private var underCircleDrawableId: Int = 0
        private var logoWidthRatio: Float = 0f
        private var logoHeightRatio: Float = 0f
        private var logoOffSetRatio: Float = 0f
        private var onSyncClick: (() -> Unit)? = null
        private var state: ProgressButtonState? = ProgressButtonState.IDLE


        fun setPageButtonText(text: String): Builder {
            pageButtonText = text
            return this
        }

        fun setTitleText(text: String): Builder {
            mainText = text
            return this
        }

        fun setLogoDrawableId(drawableId: Int): Builder {
            logoDrawableId = drawableId
            return this
        }

        fun setCircleDrawableId(drawableId: Int): Builder {
            circleDrawableId = drawableId
            return this
        }

        fun setUnderCircleDrawableId(underCircleDrawableId: Int): Builder {
            this.underCircleDrawableId = underCircleDrawableId
            return this
        }

        fun setLogoWidthRatio(ratio: Float): Builder {
            logoWidthRatio = ratio
            return this
        }

        fun setHeightRatio(ratio: Float): Builder {
            logoHeightRatio = ratio
            return this
        }

        fun setLogoOffSetRatio(ratio: Float): Builder {
            logoOffSetRatio = ratio
            return this
        }

        fun setOnSyncButtonClickListener(onClick: () -> Unit): Builder {
            onSyncClick = onClick
            return this
        }

        fun build(): PageData {
           return PageData(
                pageButtonText ?: throw NullPointerException("page button text must be set"),
                mainText ?: throw NullPointerException("main text must be set"),
                if (logoDrawableId == 0) throw IllegalStateException("logo drawable id must be set") else logoDrawableId,
                if (circleDrawableId == 0) throw IllegalStateException("circleDrawableId drawable id must be set") else circleDrawableId,
                if (underCircleDrawableId == 0) throw IllegalStateException("underCircleDrawableId drawable id must be set") else underCircleDrawableId,
                if (logoWidthRatio == 0f) throw IllegalStateException("logo width ratio can't be zero") else logoWidthRatio,
                if (logoHeightRatio == 0f) throw IllegalStateException("logo height ratio can't be zero") else logoHeightRatio,
                logoOffSetRatio,
                state ?: ProgressButtonState.IDLE,
                onSyncClick

            )
        }
    }

    enum class ProgressButtonState {
        IDLE, STARTED, COMPLETED, FAILED
    }
}