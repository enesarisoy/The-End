package com.ns.theend.ui.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ns.theend.R
import com.ns.theend.databinding.FragmentMovieFavoriteBinding
import com.ns.theend.databinding.FragmentTvFavoriteBinding
import com.ns.theend.ui.BaseFragment

class TvFavoriteFragment : BaseFragment<FragmentTvFavoriteBinding>(
    FragmentTvFavoriteBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

