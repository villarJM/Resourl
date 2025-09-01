package com.devvillar.resourl.features.addresource.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResourceData(
    val id: Int,
    val title: String,
    val url: String,
    val date: String,
    val description: String,
    val category: String,
    val tags: List<String>
) : Parcelable
