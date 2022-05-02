package com.joaovicttors.user_feature.domain.repositories

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.domain.entities.User

interface UserRepository {

    suspend fun getUserListFromLocalStorage(): Response<List<User>>

    suspend fun getUserListFromRemoteStorage(): Response<List<User>>

    suspend fun insertUserListOnLocalStorage(userList: List<User>): Response<Unit>
}