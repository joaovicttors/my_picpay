package com.joaovicttors.user_feature.data.datasources.local.services

import com.joaovicttors.user_feature.domain.entities.User

interface RoomUserService {

    suspend fun getUserList(): List<User>

    suspend fun insertUserList(userList: List<User>)
}