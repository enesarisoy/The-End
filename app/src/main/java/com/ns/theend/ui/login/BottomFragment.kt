package com.ns.theend.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ns.theend.R
import com.ns.theend.databinding.FragmentBottomSheetBinding

class BottomFragment() : BottomSheetDialogFragment(){

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomSheetBinding.inflate(layoutInflater)

        onClick()
        return binding.root
    }

    private fun onClick() {
        binding.tvSignUp.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}