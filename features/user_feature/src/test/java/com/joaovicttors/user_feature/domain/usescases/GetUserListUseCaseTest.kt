package com.joaovicttors.user_feature.domain.usescases

import com.joaovicttors.core.Response
import com.joaovicttors.user_feature.domain.entities.User
import com.joaovicttors.user_feature.domain.repositories.UserRepository
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class GetUserListUseCaseTest {

    private lateinit var userRepository: UserRepository
    private lateinit var useCase: GetUserListUseCase

    @Before
    fun before() {
        userRepository = mockk()
        useCase = GetUserListUseCase(userRepository)
    }

    @After
    fun after() {
    }

    @Test
    fun `given when then -`() =
        runBlocking { ->

            // given
            // dado ao acessar chamar getLocalUserList, retornar um error
            coEvery { userRepository.getUserListFromLocalStorage() } returns Response.Error("")
            coEvery { userRepository.getUserListFromRemoteStorage() } returns Response.Error("")

            // when
            // quando for chamando o use case
            useCase()

            // then
            // entao deve acessar o remote storage
            coVerify(exactly = 1) { userRepository.getUserListFromRemoteStorage() }
        }

    @Test
    fun `given when then --`() =
        runBlocking { ->
            val userList = listOf<User>()
            val expectedResponse = Response.Success(userList)

            // given
            // dado ao acessar chamar getLocalUserList, retornar um sucesso com a lista nao vazia
            coEvery { userRepository.getUserListFromLocalStorage() } returns expectedResponse

            // when
            // quando for chamando o use case
            val actualResponse = useCase()

            // then
            // entao retorna o que estiver no local storage
            assertEquals(expectedResponse, actualResponse)
        }


    @Test
    fun `given when then ---`() =
        runBlocking { ->
            val expectedResponse = Response.Error<List<User>>("")

            // given
            // dado ao acessar chamar getRemoteUserList, retornar um error
            coEvery { userRepository.getUserListFromLocalStorage() } returns expectedResponse
            coEvery { userRepository.getUserListFromRemoteStorage() } returns expectedResponse

            // when
            // quando for chamando o use case
            val actualResponse = useCase()

            // then
            // entao retorna o erro do remote storage
            assertEquals(expectedResponse, actualResponse)
        }

    @Test
    fun `given when then ----`() =
        runBlocking { ->
            val userList = listOf<User>()

            // given
            // dado ao acessar chamar getRemoteUserList, retornar um sucess
            coEvery { userRepository.getUserListFromLocalStorage() } returns Response.Error("")
            coEvery { userRepository.getUserListFromRemoteStorage() } returns Response.Success(userList)
            coEvery { userRepository.insertUserListOnLocalStorage(userList) } returns Response.Success(Unit)


            // when
            // quando for chamando o use case
            useCase()

            // then
            // entao deve inserir os dados no local storage
            coVerify(exactly = 1) { userRepository.insertUserListOnLocalStorage(userList) }
        }

    @Test
    fun `given when then -----`() =
        runBlocking { ->
            val userList = listOf<User>()
            val expectedResponse = Response.Success(userList)

            // given
            // dado ao acessar chamar getRemoteUserList, retornar um sucess
            coEvery { userRepository.getUserListFromLocalStorage() } returns Response.Error("")
            coEvery { userRepository.getUserListFromRemoteStorage() } returns expectedResponse
            coEvery { userRepository.insertUserListOnLocalStorage(userList) } returns Response.Success(Unit)

            // when
            // quando for chamando o use case
            val actualResponse = useCase()

            // then
            // entao retorna o sucesso do remote storage
            assertEquals(expectedResponse, actualResponse)
        }

    @Test
    fun `given when then ------`() =
        runBlocking { ->
            val userList = listOf<User>()
            val expectedResponse = Response.Success(userList)

            // given
            // dado ao acessar chamar insertUserListOnLocalStorage, retornar um error
            coEvery { userRepository.getUserListFromLocalStorage() } returns Response.Error("")
            coEvery { userRepository.getUserListFromRemoteStorage() } returns expectedResponse
            coEvery { userRepository.insertUserListOnLocalStorage(userList) } returns Response.Error("")

            // when
            // quando for chamando o use case
            val actualResponse = useCase()

            // then
            // entao retorna o sucesso do remote storage

            coVerify(exactly = 1) { userRepository.insertUserListOnLocalStorage(userList) }

            assertEquals(expectedResponse, actualResponse)
        }

    @Test
    fun `given when then -------`() =
        runBlocking { ->
            val userList = listOf<User>()
            val expectedResponse = Response.Success(userList)

            // given
            // dado ao acessar chamar insertUserListOnLocalStorage, retornar um sucesso
            coEvery { userRepository.getUserListFromLocalStorage() } returns Response.Error("")
            coEvery { userRepository.getUserListFromRemoteStorage() } returns expectedResponse
            coEvery { userRepository.insertUserListOnLocalStorage(userList) } returns Response.Success(Unit)

            // when
            // quando for chamando o use case
            val actualResponse = useCase()

            // then
            // entao retorna o sucesso do remote storage
            coVerify(exactly = 1) { userRepository.insertUserListOnLocalStorage(userList) }

            assertEquals(expectedResponse, actualResponse)
        }
}