package com.ns.theend.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ns.theend.utils.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRepositoryImpl @Inject constructor(
    val database: FirebaseDatabase
) : FirebaseRepository {

    private var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()

    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var uid = firebaseAuth.currentUser?.uid
    var movieIdList: List<String> = arrayListOf()


    override suspend fun getData(): List<String> {
        uid?.let {
            database.getReference("Users").child(it).child("movie").get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val result = task.result
                        result?.let { response ->
                            val map: Map<String, String> = response.value as Map<String, String>
                            movieIdList = map.values.toList()
                            Log.e("Test", movieIdList.toString())
                        }
                    } else {
                        //todo
                    }
                }.addOnFailureListener { exception ->
                    Log.e("Exception1", exception.toString())
                }.addOnSuccessListener { success ->
                    Log.e("Exception2", success.toString())
                }
        }
        Log.e("List", movieIdList.toString())

        return movieIdList
    }


    /*override fun getData(): MutableLiveData<Response> {
        database.getReference("Users").child(uid!!).child("movie").get().addOnCompleteListener { task ->
            val response = Response()
            if (task.isSuccessful) {
                val result = task.result
                result?.let {
                    *//*response.ids = result.children.map { snapShot ->
                        snapShot.getValue(String::class.java)!!
                    }*//*
                    val map: Map<String, String> = it.value as Map<String, String>
                    movieIdList = map.values.toList()
                    Log.e("Deneme",result.toString())
                }
            } else {
                response.exception = task.exception
            }
            mutableLiveData.value = response
        }.addOnFailureListener {
            Log.e("Deneme", it.toString())
        }.addOnSuccessListener {
            Log.e("Deneme", "OLDU")

        }
        return movieIdList
    }*/


/* override fun getData(id: String, result: (UiState<List<String>>) -> Unit) {
        val reference =
            database.getReference("Users").child(uid!!).child("movie").get().addOnSuccessListener {
                val tasks = arrayListOf<String?>()
                for (item in it.children) {
                    val task = item.getValue(String::class.java)
                    tasks.add(task)
                }
                result.invoke(UiState.Success(tasks.filterNotNull()))


            }.addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )addon
            }
    }*/

    /* override suspend fun getData(): List<String> {
         if (uid?.isNotEmpty() == true) {

             database.getReference("Users").child(uid!!).child("movie")
                 .addValueEventListener(object : ValueEventListener {
                     override fun onDataChange(snapshot: DataSnapshot) {
                         val map: Map<String, String> = snapshot.value as Map<String, String>
                         movieIdList = map.values.toList()
                         Log.e("Denemeee", movieIdList.toString())

                     }

                     override fun onCancelled(error: DatabaseError) {
                         TODO("Not yet implemented")
                     }
                 })
         }

         return movieIdList
     }*/
}


