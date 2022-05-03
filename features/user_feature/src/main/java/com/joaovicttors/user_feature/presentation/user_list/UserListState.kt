package com.joaovicttors.user_feature.presentation.user_list

import com.joaovicttors.user_feature.domain.entities.User

data class UserListState(
    val data: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)