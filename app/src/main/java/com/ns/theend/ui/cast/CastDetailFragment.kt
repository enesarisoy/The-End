package com.ns.theend.ui.cast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ns.theend.R
import com.ns.theend.databinding.FragmentCastDetailBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.cast.adapter.CastDetailAdapter
import com.ns.theend.ui.cast.adapter.CastPagingAdapter
import com.ns.theend.ui.detail.DetailViewModel
import com.ns.theend.ui.search.SearchViewModel
import com.ns.theend.utils.Constants.API_KEY
import com.ns.theend.utils.Constants.IMAGE_BASE_URL
import com.ns.theend.utils.Resource
import com.ns.theend.utils.downloadImage
import com.ns.theend.utils.makeVisible
import com.ns.theend.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class CastDetailFragment : BaseFragment<FragmentCastDetailBinding>(
    FragmentCastDetailBinding::inflate
) {

    private val viewModel: CastViewModel by viewModels()
    val args: CastDetailFragmentArgs by navArgs()
    private val castDetailAdapter = CastDetailAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initData()
        initClick()


    }

    private fun initClick() {
    }

    private fun initData() {
        viewModel.getCastDetail(args.id, API_KEY)

        viewModel.castDetailResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {

                    response.data?.let { result ->

                        binding.apply {
                            ivMovie.downloadImage(IMAGE_BASE_URL + result.profilePath)
                            tvName.text = result.name
                            tvDesc.text = result.biography
                            tvBirthdayResponse.text = result.birthday
                            tvPlaceOfBirthResponse.text = result.placeOfBirth
                            result.deathday?.let {
                                tvDeathDay.makeVisible()
                                tvDeathDayResponse.makeVisible()
                                tvDeathDayResponse.text = result.deathday.toString()

                            }
                            val dateNow = Calendar.getInstance().get(Calendar.YEAR)
                            val resultYear = result.birthday.substring(0, 4).toInt()

                            tvAge.text = (dateNow - resultYear).toString()


                        }
                    }
                }
                is Resource.Error -> {
                    context?.toast(response.message.toString())
                }
            }
        }

        viewModel.getPersonCredits(args.id, API_KEY)
        viewModel.creditsResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { cast ->
                        binding.apply {


                            castDetailAdapter.setData(cast)

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
        binding.rvKnownFor.apply {
            adapter = castDetailAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}