package com.ns.theend.ui.movie

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType
import com.ns.theend.R
import com.ns.theend.databinding.FragmentMovieBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.MainFragmentDirections
import com.ns.theend.ui.adapter.ViewPagerAdapter
import com.ns.theend.ui.movie.adapter.PopularAdapter
import com.ns.theend.ui.movie.adapter.TopRatedAdapter
import com.ns.theend.ui.movie.adapter.TrendingAdapter
import com.ns.theend.utils.Constants.API_KEY
import com.ns.theend.utils.Constants.IMAGE_BASE_URL
import com.ns.theend.utils.Resource
import com.ns.theend.utils.downloadImageForCarousel
import com.ns.theend.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>(
    FragmentMovieBinding::inflate
) {

    private val TAG = "MovieFragment"
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var trendingAdapter: TrendingAdapter
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var topRatedAdapter: TopRatedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObservers()
        initClick()

    }

    private fun initClick() {

        trendingAdapter.setOnItemClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToMovieDetailFragment(it, null, null)

            )
        }

        topRatedAdapter.setOnItemClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToMovieDetailFragment(it,null, null)
            )
        }

        popularAdapter.setOnItemClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToMovieDetailFragment(it, null, null)
            )
        }

        binding.apply {
            tvTrendViewAll.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToViewAllFragment("trending")
                )
            }

            tvPopularViewAll.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToViewAllFragment("popular")
                )
            }

            tvTopRatedViewAll.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToViewAllFragment("top_rated")
                )
            }

            ivUpcomingViewAll.setOnClickListener {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToViewAllFragment("upcoming")
                )
            }
        }
    }

    private fun initObservers() {
        viewModel.getTrendingMovies(API_KEY)
        viewModel.getPopularMovies(API_KEY)
        viewModel.getTopRatedMovie(API_KEY)
        viewModel.getUpcomingMovie(API_KEY)

        viewModel.trendingMoviesResponse.observe(viewLifecycleOwner) { response ->

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


        viewModel.popularMoviesResponse.observe(viewLifecycleOwner) { response ->
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

        viewModel.topRatedMoviesResponse.observe(viewLifecycleOwner) { response ->
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

        viewModel.upcomingMovieResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        val imageList: ArrayList<String> = arrayListOf()
                        for (i in 1..5) {
                            imageList.add(it.results[i].posterPath)
                        }
                        initCarousel(imageList)

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

        binding.rvTrending.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = trendingAdapter
        }

        popularAdapter = PopularAdapter()
        binding.rvPopular.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }

        topRatedAdapter = TopRatedAdapter()
        binding.rvTopRated.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedAdapter
        }
    }

    private fun initCarousel(images: List<String>) {
        binding.ivUpcoming.apply {
            size = images.size
            autoPlay = true
            resource = R.layout.item_carousel
            indicatorAnimationType = IndicatorAnimationType.FILL
            carouselOffset = OffsetType.CENTER
            setCarouselViewListener { view, position ->
                val imageView = view.findViewById<ImageView>(R.id.imgCarousel)
                var imgPath: String? = images[position]
                if (imgPath.isNullOrEmpty())
                    imgPath = ""
                imageView.downloadImageForCarousel(IMAGE_BASE_URL + imgPath)
            }
            show()
        }
    }

}