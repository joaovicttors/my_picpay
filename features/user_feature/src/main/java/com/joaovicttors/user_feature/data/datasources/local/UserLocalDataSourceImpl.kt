package com.joaovicttors.user_feature.data.datasources.local

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.data.datasources.local.services.RoomUserService
import com.joaovicttors.user_feature.domain.entities.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UserLocalDataSourceImpl(
    private val dispatcher: CoroutineDispatcher,
    private val roomUserService: RoomUserService
) : UserLocalDataSource {

    override suspend fun getUserList(): Response<List<User>> =
        withContext(dispatcher) {
            try {
                return@withContext roomUserService.getUserList().let { Response.Success(it) }
            } catch (error: Exception) {
                return@withContext Response.Error(error.message)
            }
        }


    override suspend fun insertUserList(userList: List<User>): Response<Unit> =
        withContext(dispatcher) {
            try {
                return@withContext roomUserService.insertUserList(userList).let { Response.Success(it) }
            } catch (error: Exception) {
                return@withContext Response.Error(error.message)
            }
        }
}