package com.ns.theend.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.ns.theend.R
import com.ns.theend.databinding.FragmentLoginBinding
import com.ns.theend.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {

    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClick()
        checkUser()

    }

    private fun initClick() {
        binding.tvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.floatingActionButton.setOnClickListener {
            signIn()
        }
    }

    private fun checkUser() {

        firebaseAuth.currentUser?.let {
//            findNavController().navigate(R.id.action_loginFragment_to_movieFragment)

        }
    }

    private fun signIn() {
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()

        if (email.isNotEmpty() && pass.isNotEmpty()) {

            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    findNavController().navigate(R.id.action_loginFragment_to_movieFragment)
                } else {
                    Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()

                }
            }
        } else {
            Toast.makeText(context, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
        }
    }


}