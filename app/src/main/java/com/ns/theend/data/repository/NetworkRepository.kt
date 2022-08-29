package com.ns.theend.data.repository

import com.ns.theend.data.model.MovieResponse
import com.ns.theend.data.remote.ServiceApi
import com.ns.theend.utils.Constants.API_KEY
import com.ns.theend.utils.Resource
import com.ns.theend.utils.Utils.safeApiCall
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val serviceApi: ServiceApi) {

    suspend fun getPopular(): Resource<MovieResponse> {
        return safeApiCall(call = { serviceApi.getPopular() })

    }
}