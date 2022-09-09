package com.ns.theend.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ns.theend.R
import com.ns.theend.databinding.FragmentSearchBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.all.adapter.MoviePagingAdapter
import com.ns.theend.ui.search.adapter.SearchPagingAdapter
import com.ns.theend.utils.Constants.API_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate
) {

    private val viewModel: SearchViewModel by viewModels()
    private val searchPagingAdapter = SearchPagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initSearch()
        initClick()

    }

    private fun initClick() {
        searchPagingAdapter.setOnItemClickListener {
            when (it.mediaType) {
                "movie" -> {
                    findNavController().navigate(
                        MainSearchFragmentDirections.actionMainSearchFragmentToMovieDetailFragment(null, it, null)
                    )
                }
                "tv" -> {
                    findNavController().navigate(
                        MainSearchFragmentDirections.actionMainSearchFragmentToTvDetailFragment(null, it, null)
                    )
                }
                else -> Log.e("Dene", it.toString())
            }
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

        viewModel.list.observe(viewLifecycleOwner) {
            searchPagingAdapter.submitData(lifecycle, it)
        }
    }

    private fun initRecyclerView() {
        binding.rvSearch.apply {
            adapter = searchPagingAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        }
    }
}