package com.devvillar.resourl.features.addresource.domain

data class ResourceItem(
    val id: Int,
    val title: String,
    val url: String,
    val description: String,
    val category: String,
    val tags: List<String>,
    val date: String,
)
