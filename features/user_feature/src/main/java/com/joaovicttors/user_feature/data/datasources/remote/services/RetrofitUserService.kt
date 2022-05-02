package com.joaovicttors.user_feature.data.datasources.remote.services

import com.joaovicttors.user_feature.data.models.UserResponse

interface RetrofitUserService {

    suspend fun getUserList(): List<UserResponse>
}