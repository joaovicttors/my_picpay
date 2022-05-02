package com.joaovicttors.user_feature.data.usescases

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.data.entities.User
import com.joaovicttors.user_feature.data.repositories.UserRepository

class GetUserListUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): Response<List<User>> {
        return when (val localResponse = userRepository.getUserListFromLocalStorage()) {
            is Response.Error -> callRemoteStorage()
            is Response.Success -> localResponse
        }
    }

    private suspend fun callRemoteStorage(): Response<List<User>> {
        return when (val remoteResponse = userRepository.getUserListFromRemoteStorage()) {
            is Response.Error -> remoteResponse
            is Response.Success -> remoteResponse.also { userRepository.insertUserListOnLocalStorage(it.data) }
        }
    }
}