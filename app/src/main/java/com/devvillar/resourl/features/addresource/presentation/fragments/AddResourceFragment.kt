package com.devvillar.resourl.features.addresource.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devvillar.resourl.R
import com.devvillar.resourl.features.addresource.adapters.AddResourceAdapter
import com.devvillar.resourl.features.addresource.presentation.viewmodels.AddResourceViewModel

class AddResourceFragment : Fragment() {

    private lateinit var viewModel: AddResourceViewModel
    private lateinit var adapter: AddResourceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_resource, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[AddResourceViewModel::class.java]

        setupRecyclerView(view)
        observeViewModel()
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_add_resource)
        recyclerView.layoutManager = LinearLayoutManager(context).apply {
            stackFromEnd = true
        }
    }

    private fun observeViewModel() {
        viewModel.resourceItems.observe(viewLifecycleOwner) { items ->
            adapter = AddResourceAdapter(items)
            view?.findViewById<RecyclerView>(R.id.recycler_view_add_resource)?.adapter = adapter
        }
    }
}