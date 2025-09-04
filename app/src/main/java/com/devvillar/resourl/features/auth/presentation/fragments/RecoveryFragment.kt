package com.devvillar.resourl.features.auth.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devvillar.resourl.R
import com.devvillar.resourl.features.auth.presentation.viewmodels.RecoveryViewModel

class RecoveryFragment : Fragment() {

    companion object {
        fun newInstance() = RecoveryFragment()
    }

    private val viewModel: RecoveryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_recovery, container, false)
    }
}