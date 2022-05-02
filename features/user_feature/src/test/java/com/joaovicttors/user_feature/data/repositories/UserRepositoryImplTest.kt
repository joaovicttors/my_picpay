package com.joaovicttors.user_feature.data.repositories

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.data.datasources.UserLocalDataSource
import com.joaovicttors.user_feature.data.datasources.UserRemoteDataSource
import com.joaovicttors.user_feature.domain.entities.User
import com.joaovicttors.user_feature.domain.repositories.UserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class UserRepositoryImplTest {

    private lateinit var localDataSource: UserLocalDataSource
    private lateinit var remoteDataSource: UserRemoteDataSource
    private lateinit var repository: UserRepository

    @Before
    fun before() {
        localDataSource = mockk()
        remoteDataSource = mockk()
        repository = UserRepositoryImpl(localDataSource, remoteDataSource)
    }

    @After
    fun after() {
    }

    @Test
    fun `given when then -`() =
        runBlocking { ->
            val expectedResponse = Response.Error<List<User>>("")

            // given
            // dado ao acessar chamar getUserList from local storage, retornar um error
            coEvery { localDataSource.getUserList() } returns expectedResponse

            // when
            // quando for chamando o repository
            val actualResponse = repository.getUserListFromLocalStorage()

            // then
            // entao deve retornar o error
            assertTrue(actualResponse is Response.Error)
            assertEquals(expectedResponse, actualResponse)
        }

    @Test
    fun `given when then --`() =
        runBlocking { ->
            val userList = listOf<User>()
            val expectedResponse = Response.Success(userList)

            // given
            // dado ao acessar chamar getUserList from local storage, retornar um sucess
            coEvery { localDataSource.getUserList() } returns expectedResponse

            // when
            // quando for chamando o repository
            val actualResponse = repository.getUserListFromLocalStorage()

            // then
            // entao deve retornar o sucesso
            assertTrue(actualResponse is Response.Success)
            assertEquals(expectedResponse, actualResponse)
        }

    @Test
    fun `given when then ---`() =
        runBlocking { ->
            val expectedResponse = Response.Error<List<User>>("")

            // given
            // dado ao acessar chamar getUserList from remote storage, retornar um error
            coEvery { remoteDataSource.getUserList() } returns expectedResponse

            // when
            // quando for chamando o repository
            val actualResponse = repository.getUserListFromRemoteStorage()

            // then
            // entao deve retornar o error
            assertTrue(actualResponse is Response.Error)
            assertEquals(expectedResponse, actualResponse)
        }

    @Test
    fun `given when then ----`() =
        runBlocking { ->
            val userList = listOf<User>()
            val expectedResponse = Response.Success(userList)

            // given
            // dado ao acessar chamar getUserList from remote storage, retornar um sucess
            coEvery { remoteDataSource.getUserList() } returns expectedResponse

            // when
            // quando for chamando o repository
            val actualResponse = repository.getUserListFromRemoteStorage()

            // then
            // entao deve retornar o sucesso
            assertTrue(actualResponse is Response.Success)
            assertEquals(expectedResponse, actualResponse)
        }

    @Test
    fun `given when then -----`() =
        runBlocking { ->
            val userList = listOf<User>()
            val expectedResponse = Response.Error<Unit>("")

            // given
            // dado ao acessar chamar insert user list on local storage, retornar um error
            coEvery { localDataSource.insertUserList(userList) } returns expectedResponse

            // when
            // quando for chamando o repository
            val actualResponse = repository.insertUserListOnLocalStorage(userList)

            // then
            // entao deve retornar o error
            assertTrue(actualResponse is Response.Error)
            assertEquals(expectedResponse, actualResponse)
        }

    @Test
    fun `given when then ------`() =
        runBlocking { ->
            val userList = listOf<User>()
            val expectedResponse = Response.Success(Unit)

            // given
            // dado ao acessar chamar insert user list on local storage, retornar um sucess
            coEvery { localDataSource.insertUserList(userList) } returns expectedResponse

            // when
            // quando for chamando o repository
            val actualResponse = repository.insertUserListOnLocalStorage(userList)

            // then
            // entao deve retornar o sucesso
            assertTrue(actualResponse is Response.Success)
            assertEquals(expectedResponse, actualResponse)
        }
}