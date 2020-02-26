package com.android.stripesliderview.anim

import android.animation.*
import android.view.View

@Suppress("CAST_NEVER_SUCCEEDS")
abstract class AnimationManager<T : View>(
    val animatedView: T,
    private val isSequential: Boolean = true
) : AnimationController() {

    private lateinit var animatorSet: AnimatorSet

    private var valueAnimators: MutableList<Animator>? = null

    private val animatorValues = mutableMapOf<String, PropertyValuesHolder>()

    init {
        createAnimator(isSequential)
    }

    private fun createAnimator(isSequential: Boolean) {
        createAnimatorValues(animatorValues)

        valueAnimators = createValueAnimators(animatorValues)

        if (valueAnimators.isNullOrEmpty()) throw NullPointerException("value animators cannot be empty")

        animatorSet = AnimatorSet().apply {
            if (isSequential) playSequentially(valueAnimators) else playTogether(valueAnimators)
        }
    }


    fun playAnimation() {
        play(animatorSet)
    }

    fun pauseAnimation() {
        pause(animatorSet)
    }


    abstract fun createAnimatorValues(animatorValues: MutableMap<String, PropertyValuesHolder>)

    abstract fun createValueAnimators(propertyValues: MutableMap<String, PropertyValuesHolder>): MutableList<Animator>

    class AnimatorBuilder {

        companion object {
            const val DEFAULT_DURATION = 1000L
        }

        var interpolator: TimeInterpolator? = null

        var duration: Long = DEFAULT_DURATION

        var repeatCount = 0

        fun addInterpolator(interpolator: TimeInterpolator): AnimatorBuilder {
            this.interpolator = interpolator
            return this
        }

        fun addDuration(duration: Long): AnimatorBuilder {
            this.duration = duration
            return this
        }

        fun setRepeatCount(repeatCount: Int): AnimatorBuilder {
            this.repeatCount = repeatCount
            return this
        }

        fun build(
            vararg valuesHolder: PropertyValuesHolder,
            valueAnimator: (ValueAnimator) -> Unit
        ): ValueAnimator {
            return ValueAnimator().apply {
                if (valuesHolder.isNullOrEmpty()) throw NullPointerException("values can't be empty or null in order to create ValueAnimator!")
                setValues(*valuesHolder)
                this@AnimatorBuilder.interpolator?.let { this.interpolator = it }
                duration = this@AnimatorBuilder.duration
                repeatCount = this@AnimatorBuilder.repeatCount
                addUpdateListener(valueAnimator)
            }
        }

    }
}