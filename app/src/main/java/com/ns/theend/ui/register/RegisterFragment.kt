package com.ns.theend.ui.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.ns.theend.R
import com.ns.theend.databinding.FragmentRegisterBinding
import com.ns.theend.ui.BaseFragment


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(
    FragmentRegisterBinding::inflate
) {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClick()

    }

    private fun initClick() {
        binding.tvSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.floatingActionButton.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val name = binding.etName.text.toString()
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()

        if (name.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()) {
            firebaseAuth = FirebaseAuth.getInstance()

            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Successfully created!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registerFragment_to_movieFragment)
                } else {
                    Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()

                }
            }
        } else {
            Toast.makeText(context, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
        }
    }

}