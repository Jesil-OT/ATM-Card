package com.jesil.card.domain.application

import android.app.Application
import com.jesil.card.domain.di.repositoryModule
import com.jesil.card.domain.di.roomModule
import com.jesil.card.domain.di.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CardApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CardApplication)
            modules(
                listOf(
                    viewModelModule,
                    roomModule,
                    repositoryModule
                )
            )
        }
    }
}