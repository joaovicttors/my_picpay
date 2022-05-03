package com.joaovicttors.my_picpay

import android.app.Application
import com.joaovicttors.my_picpay.di.AppModule
import com.joaovicttors.user_feature.UserModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initInjections()
    }

    private fun initInjections() {
        startKoin { ->
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MainApplication)

            modules(AppModule.allModules() + UserModule.allModules())
        }
    }
}