package com.ns.theend.ui.detail.tv

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ns.theend.R
import com.ns.theend.databinding.FragmentTvDetailBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.detail.DetailViewModel
import com.ns.theend.ui.detail.tv.adapter.TvCastAdapter
import com.ns.theend.utils.Constants.API_KEY
import com.ns.theend.utils.Constants.IMAGE_BASE_URL
import com.ns.theend.utils.Resource
import com.ns.theend.utils.downloadImage
import com.ns.theend.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvDetailFragment() : BaseFragment<FragmentTvDetailBinding>(
    FragmentTvDetailBinding::inflate
) {

    private val args: TvDetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var castAdapter: TvCastAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initData()
        initRecyclerView()
        initObserver()
    }

    private fun initObserver() {
        args.tv?.id?.let { viewModel.getDetailTv(it, API_KEY) }
        args.tv?.let { viewModel.getCreditsTv(it.id, API_KEY) }
        args.searchTv?.id?.let { viewModel.getDetailTv(it, API_KEY) }
        args.searchTv?.let { viewModel.getCreditsTv(it.id, API_KEY) }

        viewModel.detailTvResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        binding.apply {

                            tvName.text = it.name
                            tvDescription.text = it.overview
                            if (it.numberOfSeasons == 1) {
                                tvSeason.text =
                                    getString(R.string.number_of_season, it.numberOfSeasons)
                            } else {
                                tvSeason.text =
                                    getString(R.string.number_of_seasons, it.numberOfSeasons)
                            }
                            tvEpisodeCount.text =
                                getString(R.string.number_of_episodes, it.numberOfEpisodes)
                            tvGenre.text = it.genres[0].name
                            ivTv.downloadImage(IMAGE_BASE_URL + it.posterPath)

                            tvOriginalTitleAboutResponse.text = it.originalName
                            tvReleaseDateAboutResponse.text = it.firstAirDate
                            tvPopularityAboutResponse.text = it.popularity.toString()
                            tvStatusAboutResponse.text = it.status

                            //TODO point between textviews
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

        viewModel.creditsTvResponse.observe(viewLifecycleOwner) { response ->
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
        castAdapter = TvCastAdapter()
        binding.rvCast.apply {
            adapter = castAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    /* private fun initData() {
         binding.apply {
         //TODO İD
             tvName.text = args.tv.name
             tvDescription.text = args.tv.overview
             tvSeason.text = args.tv.numberOfSeasons.toString()
             tvEpisodeCount.text = args.tv.numberOfEpisodes.toString()
             args.tv.genres?.let {
                 tvGenre.text = it.toString()
             }?: kotlin.run {
                 tvGenre.text = "UnSpecified"
             }

             ivTv.downloadImage(IMAGE_URL + args.tv.posterPath)

         }
     }*/
}

