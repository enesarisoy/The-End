package com.ns.theend.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ns.theend.databinding.FragmentFavoriteBinding
import com.ns.theend.ui.BaseFragment
import com.ns.theend.ui.movie.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(
    FragmentFavoriteBinding::inflate
) {

    private var firebaseDb: FirebaseDatabase =
        FirebaseDatabase.getInstance("https://the-end-3fdda-default-rtdb.europe-west1.firebasedatabase.app")
    private val viewModel: MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       /* val ref = firebaseDb.getReference("movie")
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val uidRef: DatabaseReference = ref

        uidRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val snapshot = task.result
                Log.e("Deneme", snapshot.getValue(String::class.java).toString())
            }
        }*/


    }


}