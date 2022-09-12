package com.ns.theend.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.ns.theend.ui.OnClickStar
import com.ns.theend.utils.Response
import com.ns.theend.utils.UiState


interface FirebaseRepository {

     suspend fun getData(): List<String>
//     fun getData(id: String, result: (UiState<List<String>>) -> Unit)

//     fun onResponse()
}