package com.devvillar.resourl.features.addresource.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devvillar.resourl.R
import com.devvillar.resourl.features.addresource.adapters.AddResourceAdapter
import com.devvillar.resourl.features.addresource.adapters.OnResourceEditClickListener
import com.devvillar.resourl.features.addresource.domain.ResourceItem
import com.devvillar.resourl.shared.models.ResourceData
import com.devvillar.resourl.features.addresource.presentation.viewmodels.AddResourceViewModel
import com.devvillar.resourl.features.editresource.presentation.fragments.EditResourceFragment

class AddResourceFragment : Fragment(), OnResourceEditClickListener {

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
            adapter = AddResourceAdapter(items, this)
            view?.findViewById<RecyclerView>(R.id.recycler_view_add_resource)?.adapter = adapter
        }
    }

    override fun onEditClick(resourceItem: ResourceItem) {

        val resourceData = ResourceData(
            id = resourceItem.id,
            title = resourceItem.title,
            url = resourceItem.url,
            description = resourceItem.description,
            category = resourceItem.category,
            tags = resourceItem.tags,
            date = resourceItem.date
        )

        parentFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, EditResourceFragment.newInstance(resourceData))
            .addToBackStack(null)
            .commit()
    }
}
