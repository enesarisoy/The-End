package com.ns.theend.ui.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ns.theend.R
import com.ns.theend.databinding.FragmentLoginBinding
import com.ns.theend.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClick()

    }

    private fun initClick() {
        binding.tvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}