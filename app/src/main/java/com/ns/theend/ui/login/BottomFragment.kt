package com.ns.theend.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.ns.theend.R
import com.ns.theend.databinding.FragmentBottomSheetBinding
import com.ns.theend.utils.toast

class BottomFragment() : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomSheetBinding.inflate(layoutInflater)

        onClick()
        initClick()


        return binding.root
    }

    private fun onClick() {
        binding.tvSignUp.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun initClick() {
        binding.apply {

            tvSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            btnLogin.setOnClickListener {
                signIn()
            }

        }
    }

    private fun signIn() {
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()

        if (email.isNotEmpty() && pass.isNotEmpty()) {

            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    dismiss()
                    findNavController().navigate(R.id.action_loginFragment_to_movieFragment)
                } else {
                    context?.toast(it.exception.toString())
                }
            }
        } else {
            context?.toast("Empty fields are not allowed")
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}