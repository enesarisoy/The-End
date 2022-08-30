package com.ns.theend.ui.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.ns.theend.R
import com.ns.theend.databinding.FragmentRegisterBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.MainActivity
import com.ns.theend.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(
    FragmentRegisterBinding::inflate
) {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClick()



    }

    private fun initClick() {

        binding.apply {
            tvSignIn.setOnClickListener {
                (activity as MainActivity).onBackPressed()
            }

            ivBack.setOnClickListener {
                (activity as MainActivity).onBackPressed()
            }
<<<<<<< HEAD

=======
>>>>>>> bca9e722a62dc1c739cffc59821afbe4b063791f
            btnLogin.setOnClickListener {
                signUp()
            }
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
                    context?.toast("Successfully created!")
                    findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
                } else {
                    context?.toast(it.exception.toString())

                }
            }
        } else {
            context?.toast("Empty fields are not allowed")
        }
    }

}