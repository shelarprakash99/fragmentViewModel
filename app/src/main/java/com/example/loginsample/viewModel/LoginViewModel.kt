package com.example.loginsample.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var number = 0
    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()


    fun seconds(): LiveData<String> {
        return userName
    }

    var autofill_switch = MutableLiveData<Boolean>()
    fun getSwitchValue(): LiveData<Boolean> {
        return autofill_switch
    }

    //For login validation
    val logInResult = MutableLiveData<String>()
    fun getLogInResult(): LiveData<String> = logInResult

    fun addOne() {
        number++
        val name = "UserName:${number}"
        userName.value = name
        password.value = "pass${number}"
    }

    fun performValidation() {

        if (userName.value.isNullOrBlank()) { // Invalid credentials
            logInResult.value = "Invalid username"
            return
        }

        if (password.value.isNullOrBlank()) { // Invalid credentials
            logInResult.value = "Invalid password"
            return
        }

        logInResult.value = "Valid credentials :)"

    }

}

