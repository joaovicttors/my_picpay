package com.joaovicttors.user_feature.data.datasources.remote

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.data.datasources.remote.services.RetrofitUserService
import com.joaovicttors.user_feature.data.mappers.UserResponseMapper
import com.joaovicttors.user_feature.domain.entities.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UserRemoteDataSourceImpl(
    private val mapper: UserResponseMapper,
    private val service: RetrofitUserService,
    private val dispatcher: CoroutineDispatcher,
) : UserRemoteDataSource {

    override suspend fun getUserList(): Response<List<User>> =
        withContext(dispatcher) {
            try {
                return@withContext service.getUserList().let { data ->
                    data.map { mapper.mapToDomainEntity(it) }.let { mappedData ->
                        Response.Success(mappedData)
                    }
                }
            } catch (error: Exception) {
                return@withContext Response.Error(error.message)
            }
        }
}