package com.hakangokturk.mymovie.ui.user

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hakangokturk.mymovie.R
import com.hakangokturk.mymovie.base.BaseFragment
import com.hakangokturk.mymovie.constants.ValidationResult
import com.hakangokturk.mymovie.database.entity.User
import com.hakangokturk.mymovie.databinding.FragmentSignupBinding
import com.hakangokturk.mymovie.exception.signup.SignupErrorException
import com.hakangokturk.mymovie.exception.signup.UsernameException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding> (
    R.layout.fragment_signup
) {

    private val userViewModel: UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.signup = this

        binding?.userNameEditTextId?.addTextChangedListener { text ->
            val userName = text.toString()
            userViewModel.validateUsername(userName)
        }

        lifecycleScope.launch {
            userViewModel.validationUsernameResult.collect() { validationUsernameResult ->
                if(validationUsernameResult == ValidationResult.VALID) {
                    binding?.userNameContainerId?.setHelperTextColor(ColorStateList.valueOf(resources.getColor(R.color.lightgreen)))
                    binding?.userNameContainerId?.counterTextColor = ColorStateList.valueOf(resources.getColor(R.color.lightgreen))
                } else {
                    binding?.userNameContainerId?.setHelperTextColor(ColorStateList.valueOf(resources.getColor(R.color.red)))
                    binding?.userNameContainerId?.counterTextColor = ColorStateList.valueOf(resources.getColor(R.color.red))
                }
                binding?.userNameContainerId?.helperText = validationUsernameResult.toString()
            }
        }

        binding?.passwordEditTextId?.addTextChangedListener { text ->
            val password = text.toString()
            userViewModel.validatePassword(password)
        }

        lifecycleScope.launch {
            userViewModel.validationPasswordResult.collect() { validationPasswordResult ->
                if(validationPasswordResult == ValidationResult.VALID) {
                    binding?.passwordContainerId?.setHelperTextColor(ColorStateList.valueOf(resources.getColor(R.color.lightgreen)))
                    binding?.passwordContainerId?.counterTextColor = ColorStateList.valueOf(resources.getColor(R.color.lightgreen))
                } else {
                    binding?.passwordContainerId?.setHelperTextColor(ColorStateList.valueOf(resources.getColor(R.color.red)))
                    binding?.passwordContainerId?.counterTextColor = ColorStateList.valueOf(resources.getColor(R.color.red))
                }
            binding?.passwordContainerId?.helperText = validationPasswordResult.toString()
            }
        }

    }

    fun signupClicked(view: View) {
        val userName = binding?.userNameEditTextId?.text.toString()
        val password = binding?.passwordEditTextId?.text.toString()

        val user = User(username = userName, password = password)

        lifecycleScope.launch (Dispatchers.IO){
            try{
                val checkUser = userViewModel.insertUser(user)
                if(checkUser.data == true) {
                    launch(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Successful sign up", Toast.LENGTH_SHORT).show()
                    }
                    findNavController().navigate(R.id.action_signupFragment_to_signinFragment)
                }
            } catch (exp: Exception) {
                when(exp) {
                    is UsernameException -> {
                        launch (Dispatchers.Main){
                            Toast.makeText(requireContext(), "${exp.message}", Toast.LENGTH_LONG).show()
                        }
                    }

                    is SignupErrorException -> {
                        launch (Dispatchers.Main){
                            Toast.makeText(requireContext(), "${exp.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

    }

}