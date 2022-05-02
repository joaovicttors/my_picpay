package com.joaovicttors.user_feature.data.datasources.local

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.data.datasources.local.services.RoomUserService
import com.joaovicttors.user_feature.data.mappers.UserEntityMapper
import com.joaovicttors.user_feature.domain.entities.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UserLocalDataSourceImpl(
    private val mapper: UserEntityMapper,
    private val service: RoomUserService,
    private val dispatcher: CoroutineDispatcher
) : UserLocalDataSource {

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


    override suspend fun insertUserList(userList: List<User>): Response<Unit> =
        withContext(dispatcher) {
            try {
                return@withContext userList.map { mapper.mapFromDomainEntity(it) }.let { mappedData ->
                    service.insertUserList(mappedData).let { data ->
                        Response.Success(data)
                    }
                }
            } catch (error: Exception) {
                return@withContext Response.Error(error.message)
            }
        }
}