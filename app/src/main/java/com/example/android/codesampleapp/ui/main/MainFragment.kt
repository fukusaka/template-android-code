package com.example.android.codesampleapp.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.codesampleapp.R

class MainFragment : Fragment(R.layout.main_fragment) {

    private val viewModel: MainViewModel by viewModels()

    companion object {
        fun newInstance() = MainFragment()
    }
}
