package com.ns.theend.ui.all

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.ns.theend.data.model.movie.Result
import com.ns.theend.data.model.tv.TvResult
import com.ns.theend.databinding.FragmentMovieViewAllBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.MainFragmentDirections
import com.ns.theend.ui.all.adapter.MoviePagingAdapter
import com.ns.theend.ui.all.adapter.TvPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ViewAllFragment : BaseFragment<FragmentMovieViewAllBinding>(
    FragmentMovieViewAllBinding::inflate
) {

    private val viewModel: PagingViewModel by viewModels()
    private var moviePagingAdapter = MoviePagingAdapter()
    private var tvPagingAdapter = TvPagingAdapter()
    private val args: ViewAllFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClick()
        initView()

    }

    private fun initClick() {
        moviePagingAdapter.setOnItemClickListener {
            findNavController().navigate(
                ViewAllFragmentDirections.actionViewAllFragmentToMovieDetailFragment(it, null)
            )
        }
        tvPagingAdapter.setOnItemClickListener {
            findNavController().navigate(
                ViewAllFragmentDirections.actionViewAllFragmentToTvDetailFragment(it, null)
            )
        }
    }

    private fun initView() {
        when (args.fragment) {
            "trending" -> initData(viewModel.itemsTrendingMovie)
            "popular" -> initData(viewModel.itemsPopularMovie)
            "top_rated" -> initData(viewModel.itemsTopRatedMovie)
            "upcoming" -> initData(viewModel.itemsUpcomingMovie)
            "trendingTv" -> initDataTv(viewModel.itemsTrendingTv)
            "popularTv" -> initDataTv(viewModel.itemsPopularTv)
            "top_rated_tv" -> initDataTv(viewModel.itemsTopRatedTv)
            else -> initData(viewModel.itemsTrendingMovie)
        }
    }

    private fun initData(item: Flow<PagingData<Result>>) {
        binding.bindAdapter(moviePagingAdapter = moviePagingAdapter)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                moviePagingAdapter.loadStateFlow.collect {

                    binding.progressBar.isVisible = it.source.append is LoadState.Loading
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                item.collectLatest {
                    moviePagingAdapter.submitData(it)
                }
            }

        }
    }

    private fun initDataTv(item: Flow<PagingData<TvResult>>) {
        binding.bindAdapter(tvPagingAdapter = tvPagingAdapter)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                tvPagingAdapter.loadStateFlow.collect {

                    binding.progressBar.isVisible = it.source.append is LoadState.Loading

                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                item.collectLatest {
                    tvPagingAdapter.submitData(it)
                }
            }

        }
    }

    private fun FragmentMovieViewAllBinding.bindAdapter(moviePagingAdapter: MoviePagingAdapter) {
        rvViewAll.adapter = moviePagingAdapter
        rvViewAll.layoutManager =
            LinearLayoutManager(rvViewAll.context, LinearLayoutManager.VERTICAL, false)

    }

    private fun FragmentMovieViewAllBinding.bindAdapter(tvPagingAdapter: TvPagingAdapter) {
        rvViewAll.adapter = tvPagingAdapter
        rvViewAll.layoutManager =
            LinearLayoutManager(rvViewAll.context, LinearLayoutManager.VERTICAL, false)

    }

}