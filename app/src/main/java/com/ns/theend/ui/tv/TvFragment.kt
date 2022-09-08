package com.ns.theend.ui.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ns.theend.R
import com.ns.theend.databinding.FragmentTvBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.MainFragmentDirections
import com.ns.theend.ui.tv.adapter.PopularAdapter
import com.ns.theend.ui.tv.adapter.TopRatedAdapter
import com.ns.theend.ui.tv.adapter.TrendingAdapter
import com.ns.theend.utils.Constants.API_KEY
import com.ns.theend.utils.Resource
import com.ns.theend.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvFragment : BaseFragment<FragmentTvBinding>(
    FragmentTvBinding::inflate
) {

    private lateinit var trendingAdapter: TrendingAdapter
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var topRatedAdapter: TopRatedAdapter
    private val viewModel: TvViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObservers()
        initOnClick()
    }

    private fun initOnClick() {

        trendingAdapter.setOnItemClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToTvDetailFragment(it, null)
            )
        }

        popularAdapter.setOnItemClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToTvDetailFragment(it, null)
            )
        }

        topRatedAdapter.setOnItemClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToTvDetailFragment(it, null)
            )
        }

        binding.apply {
            tvTrendViewAll.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToViewAllFragment("trendingTv")
                )
            }
            tvPopularViewAll.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToViewAllFragment("popularTv")
                )
            }
            tvTopRatedViewAll.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToViewAllFragment("top_rated_tv")
                )
            }
        }


    }

    private fun initObservers() {
        viewModel.getTrendingTv(API_KEY)
        viewModel.getPopularTv(API_KEY)
        viewModel.getTopRatedTv(API_KEY)

        viewModel.trendingTvResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        trendingAdapter.setData(it)
                    }
                }
                is Resource.Error -> {
                    context?.toast(response.message.toString())
                }
                is Resource.Loading -> {
                }
            }

        }

        viewModel.popularTvResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        popularAdapter.setData(it)
                    }
                }
                is Resource.Error -> {
                    context?.toast(response.message.toString())
                }
                is Resource.Loading -> {
                }
            }

        }
        viewModel.topRatedTvResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        topRatedAdapter.setData(it)
                    }
                }
                is Resource.Error -> {
                    context?.toast(response.message.toString())
                }
                is Resource.Loading -> {
                }
            }

        }
    }

    private fun initRecyclerView() {
        trendingAdapter = TrendingAdapter()
        popularAdapter = PopularAdapter()
        topRatedAdapter = TopRatedAdapter()

        binding.rvTrending.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = trendingAdapter
        }
        binding.rvPopular.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }
        binding.rvTopRated.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedAdapter
        }
    }


}