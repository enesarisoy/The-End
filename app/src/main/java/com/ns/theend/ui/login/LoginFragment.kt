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
import com.ns.theend.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {

    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkUser()
        initClick()

    }

    private fun initClick() {
        binding.button.setOnClickListener {

            val bottomFragment = BottomFragment()
            bottomFragment.show(parentFragmentManager, "TAG")
        }
    }

    private fun checkUser() {
        firebaseAuth.currentUser?.let {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)

        }
    }


}