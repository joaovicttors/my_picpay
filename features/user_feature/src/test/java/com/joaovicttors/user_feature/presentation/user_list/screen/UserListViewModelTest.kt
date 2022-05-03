package com.joaovicttors.user_feature.presentation.user_list.screen

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.domain.entities.User
import com.joaovicttors.user_feature.domain.usescases.GetUserListUseCase
import com.joaovicttors.user_feature.presentation.MainCoroutineScope
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class UserListViewModelTest {

    @get:Rule
    val mainCoroutineScope = MainCoroutineScope()

    private lateinit var getUserListUseCase: GetUserListUseCase
    private lateinit var viewModel: UserListViewModel

    @Before
    fun before() {
        getUserListUseCase = mockk()
        viewModel = UserListViewModel(getUserListUseCase)
    }

    @After
    fun after() {

    }

    @Test
    fun `test test`() = runTest {
        val errorMessage = "error"
        val expectedState = UserListState(errorMessage = errorMessage)

        coEvery { getUserListUseCase() } returns Response.Error(errorMessage)

        viewModel.getUserList()

        assertEquals(expectedState, viewModel.state.value)
    }

    @Test
    fun `test test 2`() = runTest {
        val errorMessage = "error"
        val expectedState = UserListState(errorMessage = errorMessage)

        coEvery { getUserListUseCase() } throws RuntimeException(errorMessage)

        viewModel.getUserList()

        assertEquals(expectedState, viewModel.state.value)
    }

    @Test
    fun `test test 3`() = runTest {
        val userList = listOf<User>()
        val expectedState = UserListState(data = userList)

        coEvery { getUserListUseCase() } returns Response.Success(userList)

        viewModel.getUserList()

        assertEquals(expectedState, viewModel.state.value)
    }
}