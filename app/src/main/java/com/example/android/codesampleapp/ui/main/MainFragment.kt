package com.example.android.codesampleapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.codesampleapp.R
import com.example.android.codesampleapp.databinding.MainFragmentBinding
import com.example.android.codesampleapp.ext.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val binding by viewBinding(MainFragmentBinding::bind)
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener { viewModel.onClickButton() }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
