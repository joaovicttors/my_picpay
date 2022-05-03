package com.joaovicttors.user_feature.presentation.user_list.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.domain.usescases.GetUserListUseCase
import com.joaovicttors.user_feature.presentation.user_list.UserListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

    val state: StateFlow<UserListState> get() = _state
    private val _state: MutableStateFlow<UserListState> = MutableStateFlow(UserListState())

    fun getUserList() {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true) }

                getUserListUseCase().let { response ->
                    when (response) {
                        is Response.Error -> _state.update { it.copy(errorMessage = response.message) }
                        is Response.Success -> _state.update { it.copy(data = response.data) }
                    }
                }
            } catch (error: Exception) {
                _state.update { it.copy(errorMessage = error.message) }
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}