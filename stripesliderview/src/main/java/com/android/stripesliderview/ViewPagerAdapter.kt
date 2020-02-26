package com.android.stripesliderview

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.android.eminogoview.EminogoView
import com.android.stripesliderview.anim.LogoAnimationManager
import com.google.android.material.button.MaterialButton

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var logoAnimationManager = LogoAnimationManager(view.findViewById(R.id.eminogo_view_id), true)
        init {
            logoAnimationManager.playAnimation()
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