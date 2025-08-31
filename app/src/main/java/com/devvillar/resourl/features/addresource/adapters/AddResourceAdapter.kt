package com.devvillar.resourl.features.addresource.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devvillar.resourl.R
import com.devvillar.resourl.features.addresource.domain.ResourceItem
import com.devvillar.resourl.features.addresource.adapters.viewholders.HeaderViewHolder
import com.devvillar.resourl.features.addresource.adapters.viewholders.ExpandedItemViewHolder
import com.devvillar.resourl.features.addresource.adapters.viewholders.NormalItemViewHolder
import com.devvillar.resourl.features.addresource.adapters.viewholders.SpacerViewHolder

class AddResourceAdapter(private var items: List<ResourceItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM_EXPANDED = 1
        private const val TYPE_ITEM_NORMAL = 2
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        items.size - 1 -> TYPE_HEADER
        items.size -> TYPE_ITEM_EXPANDED
        items.size + 1 -> 3 // TYPE_SPACER
        else -> TYPE_ITEM_NORMAL
    }

    override fun getItemCount(): Int = items.size + 2 // +1 header, +1 spacer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.add_resource_header_recent, parent, false)
            )
            TYPE_ITEM_EXPANDED -> ExpandedItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.add_resource_item_card_expanded, parent, false)
            )
            3 -> SpacerViewHolder(
                View(parent.context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        (180 * parent.context.resources.displayMetrics.density).toInt()
                    )
                }
            )
            else -> NormalItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.add_resource_item_card_compact, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind()
            is ExpandedItemViewHolder -> holder.bind(items.last())
            is NormalItemViewHolder -> holder.bind(items[position])
            is SpacerViewHolder -> { /* No binding needed */ }
        }
    }

}