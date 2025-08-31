package com.devvillar.resourl.features.addresource.adapters.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devvillar.resourl.R
import com.devvillar.resourl.features.addresource.domain.ResourceItem
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ExpandedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val resourceName: TextView = itemView.findViewById(R.id.resource_name)
    private val resourceUrl: TextView = itemView.findViewById(R.id.resource_url)
    private val resourceDate: TextView = itemView.findViewById(R.id.resource_date)
    private val resourceDescription: TextView = itemView.findViewById(R.id.resource_description)
    private val categoryChip: Chip = itemView.findViewById(R.id.category_chip)
    private val tagsChipGroup: ChipGroup = itemView.findViewById(R.id.tags_chip_group)

    fun bind(item: ResourceItem) {
        resourceName.text = item.title
        resourceUrl.text = item.url
        resourceDate.text = item.date
        resourceDescription.text = item.description
        categoryChip.text = item.category

        // Limpiar chips anteriores
        tagsChipGroup.removeAllViews()

        // Agregar chips dinámicos para tags
        item.tags.forEach { tag ->
            val chip = Chip(itemView.context).apply {
                text = tag
                chipBackgroundColor = android.content.res.ColorStateList.valueOf(android.graphics.Color.WHITE)
                chipStrokeColor = android.content.res.ColorStateList.valueOf(itemView.context.getColor(android.R.color.darker_gray))
                chipStrokeWidth = 2f
                shapeAppearanceModel = shapeAppearanceModel.toBuilder()
                    .setAllCornerSizes(20f * itemView.context.resources.displayMetrics.density)
                    .build()
                isClickable = false
                isFocusable = false
                isCloseIconVisible = false
            }
            tagsChipGroup.addView(chip)
        }
    }
}