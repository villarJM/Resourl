package com.devvillar.resourl.features.searchresource.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devvillar.resourl.R
import com.devvillar.resourl.features.searchresource.presentation.viewmodels.SearchResourceViewModel

class SearchResourceFragment : Fragment() {

    companion object {
        fun newInstance() = SearchResourceFragment()
    }

    private val viewModel: SearchResourceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search_resource, container, false)
    }
}