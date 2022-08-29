package com.ns.theend.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ns.theend.ui.movie.now_playing.NowPlayingFragment
import com.ns.theend.ui.movie.popular.PopularFragment
import com.ns.theend.ui.movie.top_rated.TopRatedFragment
import com.ns.theend.ui.movie.upcoming.UpcomingFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(
        fragmentManager, lifecycle
    ) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                PopularFragment()
            }
            1 -> {
                TopRatedFragment()
            }
            2 -> {
                UpcomingFragment()
            }
            3 -> {
                NowPlayingFragment()
            }
            else -> PopularFragment()
        }
    }
}