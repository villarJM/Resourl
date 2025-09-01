package com.devvillar.resourl.features.addresource.presentation.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.devvillar.resourl.R
import com.devvillar.resourl.databinding.FragmentEditResourceBinding
import com.devvillar.resourl.features.addresource.presentation.models.ResourceData
import com.devvillar.resourl.features.addresource.presentation.viewmodels.EditResourceViewModel

class EditResourceFragment : Fragment() {

    companion object {

        private const val TAG = "EditResourceFragment"
        private const val ARG_RESOURCE_DATA = "resource_data"

        fun newInstance(resourceData: ResourceData): EditResourceFragment {
            return EditResourceFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_RESOURCE_DATA, resourceData)
                }
            }
        }
    }


    private var _binding: FragmentEditResourceBinding? = null
    private val binding get() = checkNotNull(_binding) { "View binding is only valid between onCreateView and onDestroyView." }

    private val viewModel: EditResourceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditResourceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        prefillForm()

        binding.editToolBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun prefillForm() {
        arguments?.getParcelable<ResourceData>(ARG_RESOURCE_DATA)?.let { data ->
            binding.apply {
                urlEditText.setText(data.url)
                titleEditText.setText(data.title)
                descriptionEditText.setText(data.description)
                categoryAutoCompleteTextView.setText(data.category)
            }
        }
    }

}