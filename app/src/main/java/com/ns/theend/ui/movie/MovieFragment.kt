package com.ns.theend.ui.movie

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.ns.theend.R
import com.ns.theend.databinding.FragmentMovieBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.adapter.ViewPagerAdapter
import com.ns.theend.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>(
    FragmentMovieBinding::inflate
) {

    private val TAG = "MovieFragment"
    private val viewModel: MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabLayout()

        initObservers()

    }

    private fun initObservers() {
        viewModel.getPopularMovies()
        /*viewModel.popularMovies.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    Log.e(TAG, response.data.toString())
                }
                is Resource.Error -> {
                    Log.e(TAG, response.message.toString())
                }
                is Resource.Loading -> {
                    Log.e(TAG, "Loading")

                }

            }

        }*/
    }

    private fun initTabLayout() {
        val adapter = ViewPagerAdapter(parentFragmentManager, lifecycle)

        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Popular"
                }
                1 -> {
                    tab.text = "Top Rated"

                }
                2 -> {
                    tab.text = "Upcoming"

                }
                3 -> {
                    tab.text = "Now Playing"

                }
            }

        }.attach()

    }
}