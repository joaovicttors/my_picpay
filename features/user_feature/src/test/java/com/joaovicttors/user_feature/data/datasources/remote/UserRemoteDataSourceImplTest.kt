package com.joaovicttors.user_feature.data.datasources.remote

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.data.datasources.local.UserLocalDataSource
import com.joaovicttors.user_feature.data.datasources.local.UserLocalDataSourceImpl
import com.joaovicttors.user_feature.data.datasources.local.services.RoomUserService
import com.joaovicttors.user_feature.data.datasources.remote.services.RetrofitUserService
import com.joaovicttors.user_feature.data.mappers.UserEntityMapper
import com.joaovicttors.user_feature.data.mappers.UserResponseMapper
import com.joaovicttors.user_feature.data.models.UserEntity
import com.joaovicttors.user_feature.data.models.UserResponse
import com.joaovicttors.user_feature.domain.entities.User
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class UserRemoteDataSourceImplTest {

    private lateinit var mapper: UserResponseMapper
    private lateinit var service: RetrofitUserService
    private lateinit var dispatcher: CoroutineDispatcher
    private lateinit var dataSource: UserRemoteDataSource

    @Before
    fun before() {
        mapper = mockk()
        service = mockk()
        dispatcher = UnconfinedTestDispatcher()
        dataSource = UserRemoteDataSourceImpl(mapper, service, dispatcher)
    }

    @After
    fun after() {
    }

    @Test
    fun `given when then -`() =
        runBlocking { ->
            val errorMessage = "error"
            val expectedResponse = Response.Error<List<User>>(errorMessage)

            // given
            // dado ao chamar get user list from room service, retornar um error
            coEvery { service.getUserList() } throws RuntimeException(errorMessage)

            // when
            // quando for chamando o data source
            val actualResponse = dataSource.getUserList()

            // then
            // entao deve retornar o error
            TestCase.assertTrue(actualResponse is Response.Error)
            TestCase.assertEquals(expectedResponse, actualResponse)
        }

    @Test
    fun `given when then --`() =
        runBlocking { ->
            val userList = listOf(fakeUser())
            val userEntityList = listOf(fakeUserResponse())
            val expectedResponse = Response.Success(userList)

            // given
            // dado ao chamar get user list from room service, retornar um success
            coEvery { mapper.mapToDomainEntity(userEntityList[0]) } returns userList[0]
            coEvery { service.getUserList() } returns userEntityList

            // when
            // quando for chamando o data source
            val actualResponse = dataSource.getUserList()

            // then
            // entao deve retornar o sucesso
            TestCase.assertTrue(actualResponse is Response.Success)
            TestCase.assertEquals(expectedResponse, actualResponse)
        }

    private fun fakeUser() = User("", "", 1, "")

    private fun fakeUserResponse() = UserResponse("", "", 1, "")
}