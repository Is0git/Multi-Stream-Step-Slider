package com.android.stripesliderview.anim

import android.animation.AnimatorSet

abstract class AnimationController {

    fun play(animatorSet: AnimatorSet) {
        animatorSet.start()
    }

    fun pause(animatorSet: AnimatorSet) {
        animatorSet.pause()
    }

    fun cancel(animatorSet: AnimatorSet) {
        animatorSet.cancel()
    }

    fun end(animatorSet: AnimatorSet) {
        animatorSet.end()
    }
}