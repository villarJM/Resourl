package com.devvillar.resourl.features.addresource.adapters

import com.devvillar.resourl.features.addresource.domain.ResourceItem

interface OnResourceEditClickListener {
    fun onEditClick(resourceItem: ResourceItem)
}
