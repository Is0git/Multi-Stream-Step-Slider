package com.android.stripesliderview.anim

import android.animation.Animator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.android.eminogoview.EminogoView

class LogoAnimationManager(animatedView: EminogoView, isSequential: Boolean) :
    AnimationManager<EminogoView>(animatedView, isSequential) {
    override fun createAnimatorValues(animatorValues: MutableMap<String, PropertyValuesHolder>) {
        val startAngleProperty = PropertyValuesHolder.ofFloat("startAngle", 180f, -45f)
        val linesY = PropertyValuesHolder.ofFloat("linesY", 0f, -10f, 10f, -5f, 15f, 0f)
        val linesX = PropertyValuesHolder.ofFloat("linesX", 0f, 10f, -10f, 5f, -15f, 0f)
        val alpha = PropertyValuesHolder.ofInt("alpha", 0, 255)

        animatorValues.apply {
            put(startAngleProperty.propertyName, startAngleProperty)
            put(linesY.propertyName, linesY)
            put(linesX.propertyName, linesX)
            put(alpha.propertyName, alpha)
        }
    }

    override fun createValueAnimators(propertyValues: MutableMap<String, PropertyValuesHolder>): MutableList<Animator> {

        val underArcAnimation =
            AnimatorBuilder().addDuration(1000).addInterpolator(FastOutSlowInInterpolator())
                .build(propertyValues["startAngle"]!!, propertyValues["alpha"]!!) {
                    animatedView.apply {
                        startAngle = it.getAnimatedValue("startAngle") as Float
                        invalidate()
                    }
                }


        val linesAnimator =
            AnimatorBuilder().addDuration(1500).addInterpolator(LinearInterpolator()).setRepeatCount(ValueAnimator.INFINITE)
                .build(propertyValues["linesX"]!!, propertyValues["linesY"]!!) {
                    animatedView.apply {
                        this.linesX = it.getAnimatedValue("linesX") as Float
                        this.linesY = it.getAnimatedValue("linesY") as Float
                        invalidate()
                    }
                }


        val circleAnimator =
            AnimatorBuilder().addDuration(100).addInterpolator(FastOutSlowInInterpolator())
                .build(propertyValues["alpha"]!!) {
                    animatedView.apply {
                        val alphaAnimatedValue = it.getAnimatedValue("alpha") as Int
                        this.circleAlpha = alphaAnimatedValue
                        this.linesAlpha = alphaAnimatedValue
                        invalidate()
                    }
                }

        val twitchAnimator =
            AnimatorBuilder().addDuration(200).addInterpolator(FastOutSlowInInterpolator())
                .build(propertyValues["alpha"]!!) {
                    animatedView.apply {
                        logoAlpha = it.getAnimatedValue("alpha") as Int
                        invalidate()
                    }
                }

        return mutableListOf(underArcAnimation, twitchAnimator, circleAnimator, linesAnimator)
    }
}