package com.ns.theend.ui.movie

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.jama.carouselview.enums.IndicatorAnimationType
import com.jama.carouselview.enums.OffsetType
import com.ns.theend.R
import com.ns.theend.data.model.movie.Result
import com.ns.theend.databinding.FragmentMovieBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.MainFragmentDirections
import com.ns.theend.ui.adapter.BannerAdapter
import com.ns.theend.ui.adapter.ViewPagerAdapter
import com.ns.theend.ui.movie.adapter.PopularAdapter
import com.ns.theend.ui.movie.adapter.TopRatedAdapter
import com.ns.theend.ui.movie.adapter.TrendingAdapter
import com.ns.theend.utils.*
import com.ns.theend.utils.Constants.API_KEY
import com.ns.theend.utils.Constants.IMAGE_BASE_URL
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
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
    private lateinit var banner: BannerViewPager<Result>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        banner = binding.ivUpcoming as BannerViewPager<Result>

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
                MainFragmentDirections.actionMainFragmentToMovieDetailFragment(it, null, null)
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
                        hideShimmerEffect()
                    }
                }
                is Resource.Error -> {
                    context?.toast(response.message.toString())
                }
                is Resource.Loading -> {
                    showShimmerEffect()
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
                        val imageList: ArrayList<Result> = arrayListOf()
                        for (i in 1..5) {
//                            imageList.add(it.results[i].posterPath)
                            imageList.add(it.results[i])
                        }
                        setupViewPager(imageList)
                        hideShimmerEffect()
//                        initCarousel(imageList)

                    }
                }
                is Resource.Error -> {
                    context?.toast(response.message.toString())
                }
                is Resource.Loading -> {
                    showShimmerEffect()
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
            /*  size = images.size
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
              show()*/
        }
    }

    private fun setupViewPager(list: List<Result>) {

        banner.apply {
            adapter = BannerAdapter()
            setLifecycleRegistry(lifecycle)
        }
            .setLifecycleRegistry(lifecycle)
            .setIndicatorStyle(IndicatorStyle.CIRCLE)
            .setIndicatorSlideMode(3)
            .setIndicatorSliderGap(resources.getDimensionPixelOffset(R.dimen.dp_6))
            .setIndicatorSliderRadius(
                resources.getDimensionPixelOffset(R.dimen.dp_4),
                resources.getDimensionPixelOffset(R.dimen.dp_5)
            )
            .setPageMargin(15)
            .setScrollDuration(800)
            .setIndicatorSlideMode(IndicatorSlideMode.WORM)
            .setRevealWidth(
                resources.getDimensionPixelOffset(R.dimen.dp_10),
                resources.getDimensionPixelOffset(R.dimen.dp_10)
            )
            .setPageStyle(1 shl 3)
            .setIndicatorSliderColor(
                ContextCompat.getColor(requireContext(), R.color.white_trans),
                ContextCompat.getColor(requireContext(), R.color.light_blue)
            )
            .create(list)
    }

    private fun showShimmerEffect() {
        binding.shimmer.startShimmer()
        binding.scrollView.makeVisibilityGone()
        binding.shimmer.makeVisible()
    }

    private fun hideShimmerEffect() {
        binding.shimmer.stopShimmer()
        binding.scrollView.makeVisible()
        binding.shimmer.makeVisibilityGone()
    }

}