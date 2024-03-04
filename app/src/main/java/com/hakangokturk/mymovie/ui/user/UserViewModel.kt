package com.hakangokturk.mymovie.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakangokturk.mymovie.exception.signup.SignupErrorException
import com.hakangokturk.mymovie.exception.signup.UsernameException
import com.hakangokturk.mymovie.database.entity.User
import com.hakangokturk.mymovie.constants.Resource
import com.hakangokturk.mymovie.constants.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private val _validationUsernameResult = MutableStateFlow(ValidationResult.REQUIRED)
    val validationUsernameResult: StateFlow<ValidationResult> get() = _validationUsernameResult

    private val _validationPasswordResult = MutableStateFlow(ValidationResult.REQUIRED)
    val validationPasswordResult: StateFlow<ValidationResult> get() = _validationPasswordResult

    fun  validateUsername(username: String) {
        viewModelScope.launch {
            _validationUsernameResult.value = checkValidity(username)
        }
    }

    fun  validatePassword(password: String) {
        viewModelScope.launch {
            _validationPasswordResult.value = checkValidity(password)
        }
    }

    private fun checkValidity(result: String): ValidationResult {

        if (result.isEmpty()) {
            return ValidationResult.REQUIRED
        }

        if (result.length < 5) {
            return ValidationResult.LENGTH_ERROR
        }

        if (!result.matches(".*[A-Z].*".toRegex())) {
            return ValidationResult.UPPERCASE_ERROR
        }

        if (!result.matches(".*[a-z].*".toRegex())) {
            return ValidationResult.LOWERCASE_ERROR
        }

        if (!result.matches(".*[@#\$%^&+=-?].*".toRegex())) {
            return ValidationResult.SPECIAL_CHARACTER_ERROR
        }

        return ValidationResult.VALID
    }


    suspend fun insertUser(user: User): Resource<Boolean> {
        if (validationPasswordResult.value.toString() != ValidationResult.VALID.toString()
            && validationUsernameResult.value.toString() != ValidationResult.VALID.toString() ) {

            throw SignupErrorException()
        }

        val canInsertUser = userRepository.insertUser(user).toInt()
        if (canInsertUser == -1) {
            throw UsernameException()
        }
        return Resource.success(true)
    }

    suspend fun isUserExists(username: String, password: String): Boolean {
        val user = userRepository.isUserExists(username, password)
        return user !=null
    }
    /*
      fun passwordContainerColorChange(): Int {
          return if (validationPasswordResult.value.toString() == ValidationResult.VALID.toString()) {
              R.color.lightgreen
          } else {
              R.color.red
          }
      }

      object BindingAdapters{
          @JvmStatic
          @BindingAdapter("helperTextTextColor")
          fun setHelperTextTextColor(textInputLayout: TextInputLayout, colorResource: Int) {
              val colorStateList = ColorStateList.valueOf(ContextCompat.getColor(textInputLayout.context, colorResource))
              textInputLayout.defaultHintTextColor = colorStateList
          }
      }

       */
}