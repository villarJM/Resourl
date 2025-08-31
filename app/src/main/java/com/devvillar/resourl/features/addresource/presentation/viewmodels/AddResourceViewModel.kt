package com.devvillar.resourl.features.addresource.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devvillar.resourl.features.addresource.domain.ResourceItem

class AddResourceViewModel : ViewModel() {

    private val _resourceItems = MutableLiveData<List<ResourceItem>>()
    val resourceItems: LiveData<List<ResourceItem>> = _resourceItems

    init {
        loadMockData()
    }

    private fun loadMockData() {
        val mockItems = listOf(
            ResourceItem(
                id = 1,
                title = "Kotlin Documentation",
                url = "https://kotlinlang.org/docs/home.html",
                description = "Official Kotlin programming language documentation.",
                category = "Programming",
                tags = listOf("Kotlin", "Documentation", "Programming"),
                date = "15, January 2023"
            ),
            ResourceItem(
                id = 2,
                title = "Android Developers",
                url = "https://developer.android.com/",
                description = "Official site for Android app development.",
                category = "Mobile Development",
                tags = listOf("Android", "Development", "Mobile"),
                date = "20, February 2023"
            ),
            ResourceItem(
                id = 3,
                title = "Jetpack Compose",
                url = "https://developer.android.com/jetpack/compose",
                description = "Modern toolkit for building native Android UI.",
                category = "UI Framework",
                tags = listOf("Jetpack", "Compose", "UI"),
                date = "10, March 2023"
            ),
            ResourceItem(
                id = 4,
                title = "Material Design 3",
                url = "https://m3.material.io/",
                description = "Google's design system for creating intuitive and beautiful products.",
                category = "Design",
                tags = listOf("Material", "Design", "UI/UX"),
                date = "05, April 2023"
            )



        )
        _resourceItems.value = mockItems
    }
}