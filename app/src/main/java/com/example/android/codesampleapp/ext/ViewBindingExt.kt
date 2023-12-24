package com.example.android.codesampleapp.ext

import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.android.codesampleapp.R
import kotlin.properties.ReadOnlyProperty

inline fun <reified T : ViewBinding> ComponentActivity.viewBinding(
    crossinline bind: ((View) -> T)
): ReadOnlyProperty<ComponentActivity, T> {
    return ReadOnlyProperty { thisRef, _ ->
        val view = thisRef.findViewById<ViewGroup>(android.R.id.content)[0]
        (view.getTag(R.id.viewBinding) as? T) ?: bind(view).also { view.setTag(R.id.viewBinding, it) }
    }
}

inline fun <reified T : ViewBinding> Fragment.viewBinding(
    crossinline bind: ((View) -> T)
): ReadOnlyProperty<Fragment, T> {
    return ReadOnlyProperty { thisRef, _ ->
        val view = thisRef.requireView()
        (view.getTag(R.id.viewBinding) as? T) ?: bind(view).also { view.setTag(R.id.viewBinding, it) }
    }
}
