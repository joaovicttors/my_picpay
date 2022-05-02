package com.joaovicttors.user_feature.data.mappers

import com.joaovicttors.user_feature.data.models.UserEntity
import com.joaovicttors.user_feature.data.models.UserResponse
import com.joaovicttors.user_feature.domain.entities.User

class UserResponseMapper {

    fun mapToDomainEntity(user: UserResponse): User {
        return User(
            user.image,
            user.name,
            user.id,
            user.username
        )
    }
}