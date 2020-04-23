package com.android.stripesliderview.viewpager

import android.animation.AnimatorSet
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.android.eminogoview.EminogoView
import com.android.stripesliderview.R
import com.android.stripesliderview.anim.LogoAnimationManager
import com.android.stripesliderview.databinding.PlaceHolderBinding
import com.android.stripesliderview.listeners.OnProgressButtonListener
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.place_holder.view.*

class ViewPagerAdapter(
    private var itemsCount: Int = 3,
    val pageDataList: MutableList<PageData> = mutableListOf()
) : RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {

    var onProgressButtonListener: OnProgressButtonListener? = null

    class MyViewHolder(val binding: PlaceHolderBinding, listener: OnProgressButtonListener?) : RecyclerView.ViewHolder(binding.root) {
        var logoAnimationManager = LogoAnimationManager(binding.eminogoViewId, true)
        init {
            binding.signUpButton.apply {
                setOnClickListener {
                    this.startAnimation()
                    listener?.onClick(this)}
//                doneLoadingAnimation(
//                    R.color.colorSurface,
//                    BitmapFactory.decodeResource(context.resources, R.drawable.done_icon)
//                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = PlaceHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onProgressButtonListener)
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

            val item = pageDataList[position]
            binding.dataItem = item
            binding.eminogoViewId.apply {
                logoHeightSizeRatio = pageDataList[position].logoHeightRatio
                logoWidthSizeRatio = pageDataList[position].logoWidthRatio
                logoOffsetRatio = pageDataList[position].logoOffSetRatio
            }
            when(item.state) {
                PageData.ProgressButtonState.COMPLETED ->  binding.signUpButton.doneLoadingAnimation(
                    R.color.colorSurface,
                    BitmapFactory.decodeResource(binding.root.context.resources, R.drawable.done_icon)
                )
                PageData.ProgressButtonState.FAILED -> binding.signUpButton.doneLoadingAnimation(
                    R.color.colorSurface,
                    BitmapFactory.decodeResource(binding.root.context.resources, R.drawable.icon_failed)
                )
                PageData.ProgressButtonState.STARTED -> binding.signUpButton.startAnimation()
                else -> Log.e("state", "IDLE")
            }

        }
    }
}