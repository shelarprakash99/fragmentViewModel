package com.example.loginsample.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.loginsample.R
import com.example.loginsample.viewModel.LoginViewModel
import com.example.loginsample.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.addOne()
        Log.i("ViewModel data", viewModel.number.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val fragmentBinding =FragmentLoginBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        binding.viewModel = viewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.lifecycleOwner = this


        viewModel.getLogInResult().observe(viewLifecycleOwner, Observer {
            if (it.equals("Valid credentials :)")){
                viewModel.logInResult.value = ""
                findNavController().navigate(R.id.loginSuccess)
            }
        })

        viewModel.seconds().observe(viewLifecycleOwner, Observer {
            //observes the change in seconds function
        })

        viewModel.getSwitchValue().observe(viewLifecycleOwner, Observer {
            Log.i("Switch value",":${it}")
        })

        binding!!.setOnSwitchChange{ buttonView, isChecked ->

            if (isChecked){
                viewModel.autofill_switch.value = true
                viewModel.addOne()
            }else{
                cleartext()
            }

        }

        return fragmentBinding.root

    }

    fun cleartext() {
        binding!!.username.setText("")
        binding!!.password.setText("")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.loginFragment = this
    }
    fun LoginClicked(){
        viewModel.performValidation()
    }

    fun SignUpClicked(){
        // Navigate to the next destination to select the flavor of the cupcakes
        findNavController().navigate(R.id.iloginfragToSignupfrag)
    }
}