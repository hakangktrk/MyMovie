package com.hakangokturk.mymovie.ui.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.hakangokturk.mymovie.R
import com.hakangokturk.mymovie.base.BaseFragment
import com.hakangokturk.mymovie.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding> (
    R.layout.fragment_welcome
) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.welcome = this
    }

    fun signinClicked(view: View) {
        findNavController().navigate(R.id.action_welcomeFragment_to_signinFragment)
    }

    fun signupClicked(view: View) {
        findNavController().navigate(R.id.action_welcomeFragment_to_signupFragment)
    }
}