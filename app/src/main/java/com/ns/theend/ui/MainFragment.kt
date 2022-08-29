package com.ns.theend.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.ns.theend.databinding.FragmentMainBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.adapter.ViewPagerAdapter


class MainFragment : BaseFragment<FragmentMainBinding>(
    FragmentMainBinding::inflate
) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabLayout()
    }


    private fun initTabLayout() {
        val adapter = ViewPagerAdapter(parentFragmentManager, lifecycle)

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

