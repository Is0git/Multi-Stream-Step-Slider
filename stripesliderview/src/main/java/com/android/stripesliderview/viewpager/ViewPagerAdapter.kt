package com.android.stripesliderview.viewpager

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.android.eminogoview.EminogoView
import com.android.stripesliderview.R
import com.android.stripesliderview.anim.LogoAnimationManager
import com.google.android.material.textview.MaterialTextView

class ViewPagerAdapter(
    private var itemsCount: Int = 3,
    val pageDataList: MutableList<PageData> = mutableListOf()
) : RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {


    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private var logoAnimationManager =
            LogoAnimationManager(view.findViewById(R.id.eminogo_view_id), true)

        init {
            logoAnimationManager.playAnimation()
            view.findViewById<CircularProgressButton>(R.id.signUpButton).setOnClickListener {
                (it as CircularProgressButton).startAnimation()
            }
            view.setOnClickListener {
                view.findViewById<CircularProgressButton>(R.id.signUpButton).doneLoadingAnimation(
                    R.color.colorSurface,
                    BitmapFactory.decodeResource(view.context.resources, R.drawable.done_icon)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.place_holder, parent, false)
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

    override fun onViewRecycled(holder: MyViewHolder) {

    }
    override fun getItemCount(): Int = pageDataList.count()
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = pageDataList[position]
        val eminogoView = holder.view.findViewById<EminogoView>(R.id.eminogo_view_id)

        eminogoView.apply {
            circleDrawableId = item.circleDrawableId
            logoDrawableId = item.logoDrawableId
            circleDrawableId = item.underCircleDrawableId
        }

        holder.view.findViewById<MaterialTextView>(R.id.sign_up_text).text = item.mainText
        holder.view.findViewById<CircularProgressButton>(R.id.signUpButton).text =
            item.pageButtonText
    }

}