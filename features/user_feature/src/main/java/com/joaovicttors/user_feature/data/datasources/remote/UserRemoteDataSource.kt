package com.joaovicttors.user_feature.data.datasources.remote

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.domain.entities.User

interface UserRemoteDataSource {

    suspend fun getUserList(): Response<List<User>>
}