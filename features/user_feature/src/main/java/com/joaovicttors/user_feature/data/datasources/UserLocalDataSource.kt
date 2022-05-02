package com.joaovicttors.user_feature.data.datasources

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.domain.entities.User

interface UserLocalDataSource {

    suspend fun getUserList(): Response<List<User>>

    suspend fun insertUserList(userList: List<User>): Response<Unit>
}