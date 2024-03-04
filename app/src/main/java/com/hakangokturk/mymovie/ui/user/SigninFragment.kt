package com.hakangokturk.mymovie.ui.user

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hakangokturk.mymovie.R
import com.hakangokturk.mymovie.base.BaseFragment
import com.hakangokturk.mymovie.databinding.FragmentSigninBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

@AndroidEntryPoint
class SigninFragment : BaseFragment<FragmentSigninBinding> (
    R.layout.fragment_signin
) {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.signin = this
    }

    fun signInClicked(view: View) {
        val userName = binding?.userNameEditTextId?.text.toString()
        val password = binding?.passwordEditTextId?.text.toString()

        lifecycleScope.launch (Dispatchers.IO){
            val isUserExist = userViewModel.isUserExists(userName, password)
            if(isUserExist) {
                launch (Dispatchers.Main){
                    try {
                        findNavController().navigate(R.id.action_signinFragment_to_dashboardFragment)
                    } catch (exp: Exception) {
                        Timber.e(exp)
                    }
                }
            } else {
                launch (Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Wrong username or password", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}