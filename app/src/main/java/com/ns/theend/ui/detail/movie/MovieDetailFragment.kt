package com.ns.theend.ui.detail.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ns.theend.R
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
import java.text.NumberFormat

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>(
    FragmentMovieDetailBinding::inflate
) {

    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var castAdapter: MovieCastAdapter
    private var firebaseDb: FirebaseDatabase =
        FirebaseDatabase.getInstance("https://the-end-3fdda-default-rtdb.europe-west1.firebasedatabase.app")
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var uid: String = FirebaseAuth.getInstance().currentUser?.uid.toString()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObservers()
        initOnClick()
        checkData()
    }

    private fun initObservers() {
        args.searchMovie?.id?.let { viewModel.getDetailMovie(it, API_KEY) }
        args.movie?.id?.let { viewModel.getDetailMovie(it, API_KEY) }
        args.movie?.let { viewModel.getCreditsMovie(it.id, API_KEY) }
        args.searchMovie?.let { viewModel.getCreditsMovie(it.id, API_KEY) }
        args.detailCast?.let { viewModel.getDetailMovie(it.id, API_KEY) }
        args.detailCast?.let { viewModel.getCreditsMovie(it.id, API_KEY) }

        viewModel.detailMovieResponse.observe(viewLifecycleOwner) { response ->

            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        binding.apply {

                            Log.e("Deneme", it.id.toString())
                            tvName.text = it.title
                            tvDescription.text = it.overview
                            tvYear.text = it.releaseDate.substring(0, 4)
                            tvStar.text = it.voteAverage.toString()
                            tvOriginalTitleAboutResponse.text = it.originalTitle

                            tvRevenueAboutResponse.text =
                                NumberFormat.getIntegerInstance().format(it.revenue)
                                    .replace(",", ".")

                            tvReleaseDateAboutResponse.text = it.releaseDate
                            tvStatusAboutResponse.text = it.status

                            tvVoteAboutResponse.text =
                                NumberFormat.getIntegerInstance().format(it.voteCount)
                                    .replace(",", ".")

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

    private fun initOnClick() {

        castAdapter.setOnItemClickListener {
            findNavController().navigate(
                MovieDetailFragmentDirections.actionMovieDetailFragmentToCastDetailFragment(it.id)
            )
        }



        binding.ivStar.setOnClickListener {
//            firebaseDb.getReference("movie").push().child("movie_id").setValue(args.movie.id)
//            firebaseDb.reference.child("movie_id").setValue(args.movie.id)
            firebaseDb.getReference("Users").child(uid).child("movie").push()
                .setValue(args.movie?.id)

//            firebaseDb.reference.child("movie").child("id").setValue(args.movie.id)
//            checkData()
        }
    }

    private fun checkData() {
        val postRef = args.movie?.let { firebaseDb.getReference("movie").child(it.title) }

        postRef?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    binding.ivStar.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_star_24
                        )
                    )
                } else {
                    Log.e("Deneme", "${args.movie?.title} not exists")

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}