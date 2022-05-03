package com.joaovicttors.user_feature.data.datasources.remote.services

import com.joaovicttors.user_feature.data.models.UserResponse
import retrofit2.http.GET

interface RetrofitUserService {

    @GET(USER_ENDPOINT)
    suspend fun getUserList(): List<UserResponse>

    companion object {
        const val USER_ENDPOINT: String = "users"
    }
}