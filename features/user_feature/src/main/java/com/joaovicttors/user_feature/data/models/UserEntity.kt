package com.joaovicttors.user_feature.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @ColumnInfo(name = IMAGE) val image: String?,
    @ColumnInfo(name = NAME) val name: String?,
    @PrimaryKey @ColumnInfo(name = ID) val id: Int?,
    @ColumnInfo(name = USERNAME) val username: String?
) {

    private companion object {
        private const val IMAGE: String = "image"
        private const val NAME: String = "name"
        private const val ID: String = "id"
        private const val USERNAME: String = "username"
    }
}