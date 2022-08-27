package com.ns.theend.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.ns.theend.R
import com.ns.theend.databinding.FragmentMovieBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.adapter.ViewPagerAdapter

class MovieFragment : BaseFragment<FragmentMovieBinding>(
    FragmentMovieBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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