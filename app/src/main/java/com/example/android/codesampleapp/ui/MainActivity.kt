package com.example.android.codesampleapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.codesampleapp.R
import com.example.android.codesampleapp.databinding.MainActivityBinding
import com.example.android.codesampleapp.ext.viewBinding
import com.example.android.codesampleapp.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.main_activity) {

    private val binding by viewBinding(MainActivityBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        binding.container.alpha = 0.5f
    }
}
