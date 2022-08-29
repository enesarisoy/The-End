package com.ns.theend.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import retrofit2.Response

object Utils {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Resource<T> {
        return try {
            val myResp = call.invoke()
            if (myResp.isSuccessful) {
                Resource.Success(myResp.body()!!)
            } else {
                Resource.Error(myResp.errorBody()?.string() ?: "Something goes wrong")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }

    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    fun <T> handleResponse(
        response: Response<T>
    ): Resource<T> {
        Log.d("handleResponse", "handleResponse: ${response.message()}")
        return when {
            response.message().toString().contains("timeout") -> {
                Resource.Error("Timeout")
            }
            response.code() == 402 -> {
                Resource.Error("Invalid API Key")
            }
            response.isSuccessful -> {
                Resource.Success(response.body()!!)
            }
            else -> {
                Resource.Error(response.message())
            }
        }

    }
}