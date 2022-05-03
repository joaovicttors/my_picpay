package com.joaovicttors.user_feature

import com.joaovicttors.user_feature.data.datasources.local.UserLocalDataSource
import com.joaovicttors.user_feature.data.datasources.local.UserLocalDataSourceImpl
import com.joaovicttors.user_feature.data.datasources.remote.UserRemoteDataSource
import com.joaovicttors.user_feature.data.datasources.remote.UserRemoteDataSourceImpl
import com.joaovicttors.user_feature.data.mappers.UserEntityMapper
import com.joaovicttors.user_feature.data.mappers.UserResponseMapper
import com.joaovicttors.user_feature.data.repositories.UserRepositoryImpl
import com.joaovicttors.user_feature.domain.repositories.UserRepository
import com.joaovicttors.user_feature.domain.usescases.GetUserListUseCase
import com.joaovicttors.user_feature.presentation.user_list.screen.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object UserModule {

    fun allModules(): List<Module> = listOf(
        dataModule,
        domainModule,
        presentationModule
    )

    private val dataModule = module {

        // MAPPERS
        single { UserEntityMapper() }
        single { UserResponseMapper() }

        // DATA SOURCES
        single<UserLocalDataSource> { UserLocalDataSourceImpl(get(), get(), get()) }
        single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get(), get(), get()) }
    }

    private val domainModule = module {

        // REPOSITORIES
        single<UserRepository> { UserRepositoryImpl(get(), get()) }

        // USE CASES
        single { GetUserListUseCase(get()) }
    }

    private val presentationModule = module {

        // VIEW MODELS
        viewModel { UserListViewModel(get()) }
    }
}