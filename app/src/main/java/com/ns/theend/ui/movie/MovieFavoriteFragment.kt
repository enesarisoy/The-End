package com.ns.theend.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ns.theend.R
import com.ns.theend.databinding.FragmentMovieFavoriteBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.FirebaseViewModel
import com.ns.theend.ui.MainActivity
import com.ns.theend.ui.movie.adapter.MovieFavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieFavoriteFragment : BaseFragment<FragmentMovieFavoriteBinding>(
    FragmentMovieFavoriteBinding::inflate
) {

    @Inject
    lateinit var firebaseDb: FirebaseDatabase

    //        FirebaseDatabase.getInstance("https://the-end-3fdda-default-rtdb.europe-west1.firebasedatabase.app")
    private val viewModel: MovieViewModel by viewModels()
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var uid: String
    private lateinit var movieFavoriteAdapter: MovieFavoriteAdapter
    private val firebaseViewModel: FirebaseViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uid = firebaseAuth.currentUser?.uid.toString()
        firebaseDb.getReference("Users").child("movie")

        getData()
        /*if (uid.isNotEmpty()) {
            getUserData()
        }*/
    }

    fun getData() {
        firebaseViewModel.getData(uid)
        firebaseViewModel.idResponse.observe(viewLifecycleOwner) {
            Log.e("Fragment", it.toString())
            binding.recyclerView.apply {
                movieFavoriteAdapter =
                    MovieFavoriteAdapter()
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = movieFavoriteAdapter
            }
            movieFavoriteAdapter.setData(it)


        }

    }


   /* private fun getUserData() {

        firebaseDb.getReference("Users").child(uid).child("movie").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val map: Map<String, Any> = snapshot.value as Map<String, Any>
                val movieIdList: ArrayList<Any> = map.values.toList() as ArrayList<Any>

                movieFavoriteAdapter = MovieFavoriteAdapter(movieIdList)
                binding.recyclerView.apply {
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    adapter = movieFavoriteAdapter
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }*/
}

