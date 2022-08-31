package com.ns.theend.ui.detail.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ns.theend.databinding.FragmentMovieDetailBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.detail.DetailViewModel
import com.ns.theend.ui.detail.movie.adapter.MovieCastAdapter
import com.ns.theend.utils.Constants.API_KEY
import com.ns.theend.utils.Constants.IMAGE_BASE_URL
import com.ns.theend.utils.Resource
import com.ns.theend.utils.downloadImage
import com.ns.theend.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>(
    FragmentMovieDetailBinding::inflate
) {

    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var castAdapter: MovieCastAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObservers()
    }

    private fun initObservers() {
        viewModel.getDetailMovie(args.movie.id, API_KEY)
        viewModel.getCreditsMovie(args.movie.id, API_KEY)

        viewModel.detailMovieResponse.observe(viewLifecycleOwner) { response ->

            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        binding.apply {

                            tvName.text = it.title
                            tvDescription.text = it.overview
                            tvYear.text = it.releaseDate
                            tvStar.text = it.voteAverage.toString()
                            tvOriginalTitleAboutResponse.text = it.originalTitle
                            //TODO split revenue
                            tvRevenueAboutResponse.text = it.revenue.toString()
                            tvReleaseDateAboutResponse.text = it.releaseDate
                            tvStatusAboutResponse.text = it.status
                            tvVoteAboutResponse.text = it.voteCount.toString()

                            ivMovie.downloadImage(IMAGE_BASE_URL + it.posterPath)
                            //TODO
                            for (i in 0..it.genres.size) {
                                tvGenreAboutResponse.text = it.genres[i].name
                            }


                        }
                    }
                }
                is Resource.Error -> {
                    context?.toast(response.message.toString())
                }
                is Resource.Loading -> {

                }

            }


        }

        viewModel.creditsMovieResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { cast ->
                        binding.apply {

                            castAdapter.setData(cast)

                        }

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
        castAdapter = MovieCastAdapter()
        binding.rvCast.apply {
            adapter = castAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}