package com.joaovicttors.user_feature.data.repositories

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.data.datasources.local.UserLocalDataSource
import com.joaovicttors.user_feature.data.datasources.remote.UserRemoteDataSource
import com.joaovicttors.user_feature.domain.entities.User
import com.joaovicttors.user_feature.domain.repositories.UserRepository

class UserRepositoryImpl(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUserListFromLocalStorage(): Response<List<User>> {
        return localDataSource.getUserList()
    }

    override suspend fun getUserListFromRemoteStorage(): Response<List<User>> {
        return remoteDataSource.getUserList()
    }

    override suspend fun insertUserListOnLocalStorage(userList: List<User>): Response<Unit> {
        return localDataSource.insertUserList(userList)
    }
}