package com.ns.theend.ui.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ns.theend.ui.movie.MovieFragment
import com.ns.theend.ui.search.SearchCastFragment
import com.ns.theend.ui.search.SearchFragment
import com.ns.theend.ui.tv.TvFragment

class ViewPagerSearchAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(
        fragmentManager, lifecycle
    ) {

    val fragments: MutableList<Fragment> = mutableListOf()

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                SearchFragment()
            }
            1 -> {
                SearchCastFragment()
            }

            else -> SearchFragment()
        }
    }
}