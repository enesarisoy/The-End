package com.ns.theend.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ns.theend.R
import com.ns.theend.databinding.FragmentMovieBinding
import com.ns.theend.ui.BaseFragment

class MovieFragment : BaseFragment<FragmentMovieBinding>(
    FragmentMovieBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}