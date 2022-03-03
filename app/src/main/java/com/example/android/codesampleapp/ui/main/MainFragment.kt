package com.example.android.codesampleapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.codesampleapp.R
import com.example.android.codesampleapp.databinding.MainFragmentBinding
import com.example.android.codesampleapp.ext.dataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val binding: MainFragmentBinding? by dataBinding()
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.viewModel = viewModel
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
