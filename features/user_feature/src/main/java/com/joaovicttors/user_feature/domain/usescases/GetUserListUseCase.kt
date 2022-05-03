package com.joaovicttors.user_feature.domain.usescases

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.domain.entities.User
import com.joaovicttors.user_feature.domain.repositories.UserRepository

// TODO joao.santana modificar o unit test tambem
class GetUserListUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): Response<List<User>> {
        return when (val localResponse = userRepository.getUserListFromLocalStorage()) {
            is Response.Error -> callRemoteStorage()
            is Response.Success -> {
                if (localResponse.data.isEmpty()) {
                    return callRemoteStorage()
                }

                return localResponse
            }
        }
    }

    private suspend fun callRemoteStorage(): Response<List<User>> {
        return when (val remoteResponse = userRepository.getUserListFromRemoteStorage()) {
            is Response.Error -> remoteResponse
            is Response.Success -> remoteResponse.also { userRepository.insertUserListOnLocalStorage(it.data) }
        }
    }
}