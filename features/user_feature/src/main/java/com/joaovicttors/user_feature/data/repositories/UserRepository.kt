package com.joaovicttors.user_feature.data.repositories

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.data.entities.User

interface UserRepository {

    suspend fun getUserListFromLocalStorage(): Response<List<User>>

    suspend fun getUserListFromRemoteStorage(): Response<List<User>>

    suspend fun insertUserListOnLocalStorage(userList: List<User>): Response<Unit>
}