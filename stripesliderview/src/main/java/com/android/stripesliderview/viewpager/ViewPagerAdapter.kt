package com.android.stripesliderview.viewpager

import android.animation.AnimatorSet
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.android.eminogoview.EminogoView
import com.android.stripesliderview.R
import com.android.stripesliderview.anim.LogoAnimationManager
import com.android.stripesliderview.databinding.PlaceHolderBinding
import com.google.android.material.textview.MaterialTextView

class ViewPagerAdapter(
    private var itemsCount: Int = 3,
    val pageDataList: MutableList<PageData> = mutableListOf()
) : RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {


    class MyViewHolder(val binding: PlaceHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        var logoAnimationManager = LogoAnimationManager(binding.eminogoViewId, true)

        init {
            binding.apply {
                signUpButton.setOnClickListener { signUpButton.startAnimation() }
//                signUpButton.doneLoadingAnimation(
//                    R.color.colorSurface,
//                    BitmapFactory.decodeResource(root.context.resources, R.drawable.done_icon)
//                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = PlaceHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun addPages(pageData: List<PageData>) {
        pageDataList.addAll(pageData)
        pageDataList.apply {
            if (count() in (itemsCount + 1) until itemsCount) {
                throw IllegalArgumentException("list size must match with viewpager size passed in SliderLayout")
            } else notifyDataSetChanged()
        }
    }

    override fun onViewRecycled(holder: MyViewHolder) {
        holder.logoAnimationManager.pauseAnimation()
    }


    override fun onViewDetachedFromWindow(holder: MyViewHolder) {

    }



    override fun getItemCount(): Int = pageDataList.count()
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            logoAnimationManager.playAnimation()
            binding.dataItem = pageDataList[position]
            binding.eminogoViewId.apply {
                logoHeightSizeRatio = pageDataList[position].logoHeightRatio
                logoWidthSizeRatio = pageDataList[position].logoWidthRatio
                logoOffsetRatio = pageDataList[position].logoOffSetRatio
            }
        }
    }
}