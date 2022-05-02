package com.joaovicttors.user_feature.data.mappers

import com.joaovicttors.user_feature.data.models.UserEntity
import com.joaovicttors.user_feature.domain.entities.User

class UserEntityMapper {

    fun mapToDomainEntity(user: UserEntity): User {
        return User(
            user.image,
            user.name,
            user.id,
            user.username
        )
    }

    fun mapFromDomainEntity(user: User) : UserEntity {
        return UserEntity(
            user.image,
            user.name,
            user.id,
            user.username
        )
    }
}