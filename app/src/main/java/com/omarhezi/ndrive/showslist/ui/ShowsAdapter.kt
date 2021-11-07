package com.omarhezi.ndrive.showslist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omarhezi.ndrive.databinding.ShowListitemLayoutBinding
import com.omarhezi.ndrive.showslist.core.modules.ShowViewData

class ShowsAdapter : RecyclerView.Adapter<ShowViewHolder>() {

    var items: List<ShowViewData> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ShowViewHolder(
        ShowListitemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size
}