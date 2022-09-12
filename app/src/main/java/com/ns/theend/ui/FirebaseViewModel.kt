package com.ns.theend.ui

import android.app.Application
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ns.theend.data.repository.FirebaseRepository
import com.ns.theend.ui.movie.adapter.MovieFavoriteAdapter
import com.ns.theend.utils.Resource
import com.ns.theend.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(
    val repository: FirebaseRepository
) : ViewModel() {


    private var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var uid = firebaseAuth.currentUser?.uid
    private val _idResponse: MutableLiveData<List<String>> = MutableLiveData()
    val idResponse: LiveData<List<String>>
        get() = _idResponse

    private val _datas = MutableLiveData<UiState<List<String>>>()
    val data: LiveData<UiState<List<String>>>
        get() = _datas


    fun getData(id: String) = viewModelScope.launch{
//        repository.getData(id) { _datas.value = it }
        val deneme = repository.getData()

    }

    /* fun getData() = viewModelScope.launch {
         if (uid?.isNotEmpty() == true) {

             firebaseDatabase.getReference("Users").child(uid!!).child("movie")
                 .addValueEventListener(object :
                     ValueEventListener {
                     override fun onDataChange(snapshot: DataSnapshot) {
                         val map: Map<String, String> = snapshot.value as Map<String, String>
                         val movieIdList: ArrayList<String> = map.values.toList() as ArrayList<String>
                         idResponse.value = movieIdList.toString()
                        *//* movieFavoriteAdapter = MovieFavoriteAdapter(movieIdList)
                        binding.recyclerView.apply {
                            layoutManager =
                                LinearLayoutManager(
                                    requireContext(),
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                            adapter = movieFavoriteAdapter
                        }*//*

                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
        }
    }*/

}