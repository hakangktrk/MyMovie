package com.hakangokturk.mymovie.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T: ViewDataBinding> (
    @LayoutRes private val layoutResId: Int
    /*
    -generic sinifa (T) binding in ust sinifi ViewDataBinding i tanimliyoruz
    -@LayoutRes anotasyonu, bir XML düzen dosyasının (layout resource) R dosyasındaki ID'sini belirtir
    -her fragmentte (group, groups, facts ..) farkli xml id si aliyoruz. onuda int olarak gecicez
     */
): Fragment() {

    private var _binding: T? =null
    open val binding: T? get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding?.root
    }
}