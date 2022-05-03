package com.joaovicttors.user_feature.data.datasources.local.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joaovicttors.user_feature.data.models.UserEntity

@Dao
interface RoomUserService {

    @Query("SELECT * FROM user")
    suspend fun getUserList(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserList(userList: List<UserEntity>)
}