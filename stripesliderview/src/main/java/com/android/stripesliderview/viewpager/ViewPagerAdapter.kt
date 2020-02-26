package com.android.stripesliderview.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.stripesliderview.R
import com.android.stripesliderview.anim.LogoAnimationManager
import com.android.stripesliderview.slider.SliderPageLayout

class ViewPagerAdapter(private var itemsCount: Int = 3, val pageDataList: MutableList<PageData> = mutableListOf()) : RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {



    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private var logoAnimationManager = LogoAnimationManager(view.findViewById(R.id.eminogo_view_id), true)
        init {
            logoAnimationManager.playAnimation()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_layout, parent, false)
        return MyViewHolder(
            viewHolder
        )
    }

    fun addPages(pageData: List<PageData>) {
        pageDataList.addAll(pageData)
        pageDataList.apply {
            if (count() in (itemsCount + 1) until itemsCount) {
                throw IllegalArgumentException("list size must match with viewpager size passed in SliderLayout")
            } else notifyDataSetChanged()
        }
    }


    override fun getItemCount(): Int = pageDataList.count()
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = pageDataList[position]
        (holder.view as SliderPageLayout).apply {

            eminogoView.apply {
                circleDrawableId = item.circleDrawableId
                logoDrawableId = item.logoDrawableId
                circleDrawableId = item.underCircleDrawableId
            }

            mainTextView.text = item.mainText
            mainButton.text = item.pageButtonText
        }
    }
}