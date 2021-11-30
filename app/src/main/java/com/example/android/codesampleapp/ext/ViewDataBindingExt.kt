package com.example.android.codesampleapp.ext

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import com.example.android.codesampleapp.R
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass

// ref: https://zenn.dev/hkusu/articles/cf9357b3549c8c
// ref: https://satoshun.github.io/2020/01/fragment-view-memory-leak/
//      https://satoshun.github.io/2019/04/jetpack-coroutine-support/

fun <T : ViewDataBinding> FragmentActivity.dataBinding(): ReadOnlyProperty<FragmentActivity, T> {
    return ReadOnlyProperty { thisRef, _ ->
        val view = thisRef.findViewById<ViewGroup>(android.R.id.content)[0]
        checkNotNull(DataBindingUtil.bind<T>(view)).apply { lifecycleOwner = thisRef }
    }
}

fun <T : ViewDataBinding> Fragment.dataBinding(): ReadOnlyProperty<Fragment, T?> {
    return ReadOnlyProperty { thisRef, _ ->
        val view = thisRef.view ?: return@ReadOnlyProperty null
        checkNotNull(DataBindingUtil.bind<T>(view)).apply { lifecycleOwner = thisRef.viewLifecycleOwner }
    }
}

inline fun <reified T : ViewBinding> bindRequiredReflection(view: View, klass: KClass<T>): T =
    T::class.java.getMethod("bind", View::class.java).invoke(klass.java, view) as T

inline fun <reified T : ViewBinding> FragmentActivity.viewBinding(
    crossinline bind: ((View) -> T) = { it -> bindRequiredReflection(it, T::class) }
): ReadOnlyProperty<FragmentActivity, T> {
    return ReadOnlyProperty { thisRef, _ ->
        val view = thisRef.findViewById<ViewGroup>(android.R.id.content)[0]
        (view.getTag(R.id.viewBinding) as? T) ?: bind(view).also { view.setTag(R.id.viewBinding, it) }
    }
}

inline fun <reified T : ViewBinding> Fragment.viewBinding(
    crossinline bind: ((View) -> T) = { it -> bindRequiredReflection(it, T::class) }
): ReadOnlyProperty<Fragment, T?> {
    return ReadOnlyProperty { thisRef, _ ->
        val view = thisRef.view ?: return@ReadOnlyProperty null
        (view.getTag(R.id.viewBinding) as? T) ?: bind(view).also { view.setTag(R.id.viewBinding, it) }
    }
}
