package com.omarhezi.ndrive.showslist.ui

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omarhezi.ndrive.databinding.ShowListitemLayoutBinding
import com.omarhezi.ndrive.showslist.core.modules.ShowViewData

class ShowViewHolder(private val binding: ShowListitemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(show: ShowViewData) {
        binding.title.text = show.name
        binding.statusTxt.text = show.status
        binding.statusTxt.setTextColor(ContextCompat.getColor(itemView.context, show.statusColor))
        Glide.with(itemView.context)
            .load(show.image)
            .centerCrop()
            .into(binding.showPosterImg)
    }
}