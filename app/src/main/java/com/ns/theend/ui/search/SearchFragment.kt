package com.ns.theend.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ns.theend.R
import com.ns.theend.databinding.FragmentSearchBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.utils.Constants.API_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate
) {

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchAll(API_KEY, "Thor")

        viewModel.searchResponse.observe(viewLifecycleOwner){
            Log.e("SearchFragment", it.toString())
        }
    }
}