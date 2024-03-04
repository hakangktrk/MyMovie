package com.hakangokturk.mymovie.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

abstract class BaseListFragment<T: ViewDataBinding, U>(
    @LayoutRes private val layoutResId: Int
): BaseFragment<T>(layoutResId) {

    abstract fun setupAdapter(list: List<U>)
}