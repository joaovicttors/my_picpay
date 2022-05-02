package com.joaovicttors.user_feature.data.datasources.local

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.data.datasources.local.services.RoomUserService
import com.joaovicttors.user_feature.data.mappers.UserEntityMapper
import com.joaovicttors.user_feature.data.models.UserEntity
import com.joaovicttors.user_feature.domain.entities.User
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class UserLocalDataSourceImplTest {

    private lateinit var mapper: UserEntityMapper
    private lateinit var dispatcher: CoroutineDispatcher
    private lateinit var roomService: RoomUserService
    private lateinit var dataSource: UserLocalDataSource

    @Before
    fun before() {
        mapper = mockk()
        dispatcher = UnconfinedTestDispatcher()
        roomService = mockk()
        dataSource = UserLocalDataSourceImpl(mapper, dispatcher, roomService)
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
            coEvery { roomService.getUserList() } throws RuntimeException(errorMessage)

            // when
            // quando for chamando o data source
            val actualResponse = dataSource.getUserList()

            // then
            // entao deve retornar o error
            assertTrue(actualResponse is Response.Error)
            assertEquals(expectedResponse, actualResponse)
        }

    @Test
    fun `given when then --`() =
        runBlocking { ->
            val userList = listOf(fakeUser())
            val userEntityList = listOf(fakeUserEntity())
            val expectedResponse = Response.Success(userList)

            // given
            // dado ao chamar get user list from room service, retornar um success
            coEvery { mapper.mapToDomainEntity(userEntityList[0]) } returns userList[0]
            coEvery { roomService.getUserList() } returns userEntityList

            // when
            // quando for chamando o data source
            val actualResponse = dataSource.getUserList()

            // then
            // entao deve retornar o sucesso
            assertTrue(actualResponse is Response.Success)
            assertEquals(expectedResponse, actualResponse)
        }

    @Test
    fun `given when then ---`() =
        runBlocking { ->
            val userList = listOf(fakeUser())
            val userEntityList = listOf(fakeUserEntity())
            val errorMessage = "error"
            val expectedResponse = Response.Error<List<User>>(errorMessage)

            // given
            // dado ao chamar insert user list from room service, retornar um error
            coEvery { mapper.mapFromDomainEntity(userList[0]) } returns userEntityList[0]
            coEvery { roomService.insertUserList(userEntityList) } throws RuntimeException(errorMessage)

            // when
            // quando for chamando o data source
            val actualResponse = dataSource.insertUserList(userList)

            // then
            // entao deve retornar o error
            assertTrue(actualResponse is Response.Error)
            assertEquals(expectedResponse, actualResponse)
        }

    @Test
    fun `given when then ----`() =
        runBlocking { ->
            val userList = listOf(fakeUser())
            val userEntityList = listOf(fakeUserEntity())
            val expectedResponse = Response.Success(Unit)

            // given
            // dado ao chamar get insert user list from room service, retornar um success
            coEvery { mapper.mapFromDomainEntity(userList[0]) } returns userEntityList[0]
            coEvery { roomService.insertUserList(userEntityList) } returns Unit

            // when
            // quando for chamando o data source
            val actualResponse = dataSource.insertUserList(userList)

            // then
            // entao deve retornar o sucesso
            assertTrue(actualResponse is Response.Success)
            assertEquals(expectedResponse, actualResponse)
        }

    private fun fakeUser() = User("", "", 1, "")

    private fun fakeUserEntity() = UserEntity("", "", 1, "")
}