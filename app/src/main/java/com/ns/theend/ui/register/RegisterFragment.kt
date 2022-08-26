package com.ns.theend.ui.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ns.theend.R
import com.ns.theend.databinding.FragmentRegisterBinding
import com.ns.theend.ui.BaseFragment


class RegisterFragment : BaseFragment<FragmentRegisterBinding>(
    FragmentRegisterBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClick()

    }

    private fun initClick() {
        binding.tvSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

}