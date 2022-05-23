package com.joaovicttors.user_feature.presentation.user_list.screen

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.domain.entities.User
import com.joaovicttors.user_feature.domain.usescases.GetUserListUseCase
import com.joaovicttors.user_feature.presentation.MainCoroutineScope
import com.joaovicttors.user_feature.presentation.user_list.UserListState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
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

    @Test
    fun `when getUserListUseCase is calling should getUserList returns loading true on view state`() =
        runTest { ->
            val expectedLoading = true
            val expectedData = emptyList<User>()
            val expectedErrorMessage = null

            coEvery { getUserListUseCase() } coAnswers { delay(1000); Response.Success(expectedData)}

            viewModel.getUserList()

            viewModel.state.value.also { state ->
                assertEquals(expectedLoading, state.isLoading)
                assertEquals(expectedData, state.data)
                assertEquals(expectedErrorMessage, state.errorMessage)
            }
        }

    @Test
    fun `when getUserListUseCase returns error response should getUserList returns error message on view state`() =
        runTest { ->
            val expectedLoading = false
            val expectedData = emptyList<User>()
            val expectedErrorMessage = "Error"

            coEvery { getUserListUseCase() } returns Response.Error(expectedErrorMessage)

            viewModel.getUserList()

            viewModel.state.value.also { state ->
                assertEquals(expectedLoading, state.isLoading)
                assertEquals(expectedData, state.data)
                assertEquals(expectedErrorMessage, state.errorMessage)
            }
        }

    @Test
    fun `when getUserListUseCase returns success response should getUserList returns user list on view state`() =
        runTest { ->
            val expectedLoading = false
            val expectedData = listOf<User>(mockk())
            val expectedErrorMessage = null

            coEvery { getUserListUseCase() } returns Response.Success(expectedData)

            viewModel.getUserList()

            viewModel.state.value.also { state ->
                assertEquals(expectedLoading, state.isLoading)
                assertEquals(expectedData, state.data)
                assertEquals(expectedErrorMessage, state.errorMessage)
            }
        }

    @Test
    fun `when getUserListUseCase throws an error should getUserList returns error message on view state`() =
        runTest { ->
            val expectedLoading = false
            val expectedData = emptyList<User>()
            val expectedErrorMessage = "Error"

            coEvery { getUserListUseCase() } throws RuntimeException(expectedErrorMessage)

            viewModel.getUserList()

            viewModel.state.value.also { state ->
                assertEquals(expectedLoading, state.isLoading)
                assertEquals(expectedData, state.data)
                assertEquals(expectedErrorMessage, state.errorMessage)
            }
        }
}