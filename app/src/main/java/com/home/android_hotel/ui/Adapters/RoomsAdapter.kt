package com.home.fooddelivery.ui.menu.adapters.ViewPagerAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.home.android_hotel.R
import com.home.android_hotel.databinding.ItemPageBinding
import com.squareup.picasso.Picasso

class RoomsAdapter : ListAdapter<String, RoomsAdapter.ViewHolder>(DiffCallback()) {


    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPageBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val imageUrl = getItem(position)

        with(holder) {

            Picasso.get().load(imageUrl)
                .placeholder(R.drawable.room_placeholder)
                .into(binding.imageView)
        }
    }

    inner class ViewHolder(val binding: ItemPageBinding) : RecyclerView.ViewHolder(binding.root)

}

