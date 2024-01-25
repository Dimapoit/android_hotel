package com.home.fooddelivery.ui.menu.adapters.ViewPagerAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.home.android_hotel.databinding.PeculiarityItemBinding

class PeculiaritiesAdapter : ListAdapter<String, PeculiaritiesAdapter.ViewHolder>(PeculiaritiesAdapter.DiffCallback()) {

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
        val binding = PeculiarityItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.peculiarityItem.text = getItem(position)
        }
    }

    inner class ViewHolder(val binding: PeculiarityItemBinding) : RecyclerView.ViewHolder(binding.root)


}

