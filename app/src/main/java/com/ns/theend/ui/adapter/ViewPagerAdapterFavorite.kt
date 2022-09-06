package com.ns.theend.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ns.theend.ui.movie.MovieFavoriteFragment
import com.ns.theend.ui.movie.MovieFragment
import com.ns.theend.ui.tv.TvFavoriteFragment
import com.ns.theend.ui.tv.TvFragment

class ViewPagerAdapterFavorite(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(
        fragmentManager, lifecycle
    ) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                MovieFavoriteFragment()
            }
            1 -> {
                TvFavoriteFragment()
            }

            else -> MovieFavoriteFragment()
        }
    }
}