package com.joaovicttors.my_picpay.builders

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joaovicttors.user_feature.data.datasources.local.services.RoomUserService
import com.joaovicttors.user_feature.data.models.UserEntity

// TODO joao.santana
@Database(version = 1, entities = [UserEntity::class])
abstract class DatabaseTest: RoomDatabase() {

    abstract fun userService(): RoomUserService
}