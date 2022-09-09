package com.ns.theend.ui.cast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ns.theend.R
import com.ns.theend.databinding.FragmentCastDetailBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.cast.adapter.CastPagingAdapter
import com.ns.theend.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CastDetailFragment : BaseFragment<FragmentCastDetailBinding>(
    FragmentCastDetailBinding::inflate
) {

    private val viewModel: SearchViewModel by viewModels()
    private val castPagingAdapter = CastPagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.setQuery(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        viewModel.castList.observe(viewLifecycleOwner) {
            castPagingAdapter.submitData(lifecycle, it)
        }

    }

    private fun initRecyclerView() {
        binding.rvSearch.apply {
            adapter = castPagingAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        }
    }
}