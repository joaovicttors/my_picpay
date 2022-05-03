package com.joaovicttors.my_picpay.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.joaovicttors.my_picpay.builders.DatabaseTest
import com.joaovicttors.user_feature.data.datasources.remote.services.RetrofitUserService
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object AppModule {

    fun allModules(): List<Module> = listOf(
        providersModule,
        servicesModule,
    )

    private val providersModule = module {

        single {
            Retrofit.Builder()
                .baseUrl("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/")
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

        single {
            Room.databaseBuilder(androidApplication(), DatabaseTest::class.java, "my_picpay_db")
                .build()
        }
    }

    private val servicesModule = module {

        // USER SERVICES
        single { get<Retrofit>().create<RetrofitUserService>() }
        single { get<DatabaseTest>().userService() }
    }
}