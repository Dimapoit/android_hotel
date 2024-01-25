package com.home.fooddelivery.ui.menu.adapters.ViewPagerAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.home.android_hotel.R
import com.home.android_hotel.databinding.ItemPageBinding

class HotelAdapter : RecyclerView.Adapter<HotelAdapter.PagerVH>() {

    val imageArray = arrayOf(
        R.drawable.slider1,
        R.drawable.slider2,
        R.drawable.slider3,
        R.drawable.slider4,
        R.drawable.slider5
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPageBinding.inflate(inflater, parent, false)

        return PagerVH(binding)
    }

    override fun getItemCount(): Int = imageArray.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        with(holder) {
            binding.imageView.setImageResource(imageArray[position])
        }
    }

    inner class PagerVH(val binding: ItemPageBinding) : RecyclerView.ViewHolder(binding.root)

    fun setDotsIndicator(dotsLayout: LinearLayout, currentIndex: Int) {
        dotsLayout.removeAllViews()

        for (i in 0 until itemCount) {
            val dot = ImageView(dotsLayout.context)
            dot.setImageResource(R.drawable.dot_selector)

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(8, 0, 8, 0)
            dot.layoutParams = params

            dotsLayout.addView(dot)
        }

        if (itemCount > 0) {
            dotsLayout.getChildAt(currentIndex).isSelected = true
        }
    }
}

