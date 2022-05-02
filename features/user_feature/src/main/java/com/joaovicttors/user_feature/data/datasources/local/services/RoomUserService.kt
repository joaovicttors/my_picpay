package com.joaovicttors.user_feature.data.datasources.local.services

import com.joaovicttors.user_feature.data.models.UserEntity

interface RoomUserService {

    suspend fun getUserList(): List<UserEntity>

    suspend fun insertUserList(userList: List<UserEntity>)
}