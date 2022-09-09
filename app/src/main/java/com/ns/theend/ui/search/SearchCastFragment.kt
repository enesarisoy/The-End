package com.ns.theend.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ns.theend.R
import com.ns.theend.databinding.FragmentSearchCastBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.search.adapter.SearchCastAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCastFragment : BaseFragment<FragmentSearchCastBinding>(
    FragmentSearchCastBinding::inflate
) {

    private val viewModel: SearchViewModel by viewModels()
    private val searchCastAdapter = SearchCastAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initSearch()
        initClick()

    }

    private fun initClick() {
        searchCastAdapter.setOnItemClickListener {
            findNavController().navigate(
                MainSearchFragmentDirections.actionMainSearchFragmentToCastDetailFragment(it.id)
            )
        }
    }

    private fun initSearch() {
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
            searchCastAdapter.submitData(lifecycle, it)
        }
    }

    private fun initRecyclerView() {
        binding.rvSearch.apply {
            adapter = searchCastAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        }
    }
}
