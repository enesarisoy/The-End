package com.ns.theend.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ns.theend.data.model.movie_detail.MovieDetailResponse
import com.ns.theend.databinding.FragmentFavoriteBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.movie.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList


@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(
    FragmentFavoriteBinding::inflate
) {

    private var firebaseDb: FirebaseDatabase =
        FirebaseDatabase.getInstance("https://the-end-3fdda-default-rtdb.europe-west1.firebasedatabase.app")
    private val viewModel: MovieViewModel by viewModels()
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var uid: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        uid = firebaseAuth.currentUser?.uid.toString()
        firebaseDb.getReference("Users").child("movie")

        if (uid.isNotEmpty()) {
            getUserData()
        }


    }

    private fun getUserData() {

        firebaseDb.getReference("Users").child(uid).child("movie").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val map: Map<String, Any> = snapshot.value as Map<String, Any>
                val movieIdList: ArrayList<Any> = map.values.toList() as ArrayList<Any>

                Log.e("Deneme", movieIdList[0].toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


}