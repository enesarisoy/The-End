package com.ns.theend.ui.search

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.ns.theend.databinding.FragmentMainSearchBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.adapter.ViewPagerSearchAdapter

class MainSearchFragment : BaseFragment<FragmentMainSearchBinding>(
    FragmentMainSearchBinding::inflate
) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabLayout()
        binding.viewPager2.isUserInputEnabled = false


    }

    private fun initTabLayout() {
        val adapter =
            ViewPagerSearchAdapter(childFragmentManager, lifecycle)

        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Movie - Tv"
                }
                1 -> {
                    tab.text = "Cast"

                }
            }

        }.attach()

    }

}
