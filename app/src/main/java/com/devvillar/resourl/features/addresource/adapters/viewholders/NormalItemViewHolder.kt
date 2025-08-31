package com.devvillar.resourl.features.addresource.adapters.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devvillar.resourl.R
import com.devvillar.resourl.features.addresource.domain.ResourceItem

class NormalItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val resourceImage: ImageView = itemView.findViewById(R.id.resource_image)
    private val resourceName: TextView = itemView.findViewById(R.id.resource_name)
    private val resourceUrl: TextView = itemView.findViewById(R.id.resource_url)
    private val resourceDate: TextView = itemView.findViewById(R.id.resource_date)

    fun bind(item: ResourceItem) {
        resourceName.text = item.title
        resourceUrl.text = item.url
        resourceDate.text = item.date
    }
}