package com.android.stripesliderview

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.android.eminogoview.EminogoView
import com.google.android.material.button.MaterialButton

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {


    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var listOfAnimators = mutableListOf<Animator>()
        var togetherAnimators = mutableListOf<Animator>()

        init {
            view.findViewById<MaterialButton>(R.id.signUpButton).setOnClickListener {
                val startAngleProperty = PropertyValuesHolder.ofFloat("startAngle", 180f, -45f)
                val linesY = PropertyValuesHolder.ofFloat("linesY", 0f, -10f, 10f, -5f, 15f, 0f)
                val linesX = PropertyValuesHolder.ofFloat("linesX", 0f, 10f, -10f, 5f, -15f, 0f)
                val alpha = PropertyValuesHolder.ofInt("alpha",0, 255)


                val underArcAnimation = ValueAnimator().apply {
                    setValues(startAngleProperty, alpha)
                    duration = 1000
                    interpolator = FastOutSlowInInterpolator()

                    addUpdateListener {
                        val view = view.findViewById<EminogoView>(R.id.eminogo_view_id).apply {
                            startAngle = it.getAnimatedValue("startAngle") as Float
                        }
                        view.invalidate()
                    }
                }

                val linesAnimator = ValueAnimator().apply {
                    setValues(linesX, linesY)
                    duration = 1000
                    repeatCount = ValueAnimator.INFINITE
                    interpolator = LinearInterpolator()
                    addUpdateListener {
                        val view = view.findViewById<EminogoView>(R.id.eminogo_view_id).apply {
                            this.linesX = it.getAnimatedValue("linesX") as Float
                            this.linesY = it.getAnimatedValue("linesY") as Float
                        }
                        view.invalidate()
                    }
                }

                val circleAnimator = ValueAnimator().apply {
                    setValues(alpha)
                    duration = 500
//                    repeatCount = ValueAnimator.INFINITE
                    interpolator = FastOutSlowInInterpolator()
                    addUpdateListener {
                        val view = view.findViewById<EminogoView>(R.id.eminogo_view_id).apply {
                            val alphaAnimatedValue = it.getAnimatedValue("alpha") as Int
                            circleAlpha = alphaAnimatedValue
                            linesAlpha = it.getAnimatedValue("alpha") as Int
                        }
                        view.invalidate()
                    }
                }

                val twitchAnimator = ValueAnimator().apply {
                    setValues(alpha)
                    duration = 250
//                    repeatCount = ValueAnimator.INFINITE
                    interpolator = FastOutSlowInInterpolator()
                    addUpdateListener {
                        val view = view.findViewById<EminogoView>(R.id.eminogo_view_id).apply {
                            val alphaAnimatedValue = it.getAnimatedValue("alpha") as Int
                            twichLogoAlpha = alphaAnimatedValue
                        }
                        view.invalidate()
                    }
                }

                listOfAnimators.apply {
                    add(underArcAnimation)
                    add(twitchAnimator)
                    add(circleAnimator)
                    add(linesAnimator)
                }

                AnimatorSet().apply {

                    playSequentially(listOfAnimators)
                    start()
                }

            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_layout, parent, false)
        return MyViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = 3
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }
}