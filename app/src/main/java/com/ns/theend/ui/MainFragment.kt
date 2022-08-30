package com.ns.theend.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.ns.theend.databinding.FragmentMainBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.adapter.ViewPagerAdapter
import com.ns.theend.ui.movie.MovieViewModel
import com.ns.theend.ui.movie.adapter.TrendingAdapter
import com.ns.theend.utils.Constants
import com.ns.theend.utils.Resource
import com.ns.theend.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(
    FragmentMainBinding::inflate
) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabLayout()

        binding.viewPager2.isUserInputEnabled = false

    }


    private fun initTabLayout() {
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Movie"
                }
                1 -> {
                    tab.text = "TV"

                }
            }

        }.attach()

    }
}

