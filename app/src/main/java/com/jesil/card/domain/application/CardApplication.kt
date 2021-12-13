package com.jesil.card.domain.application

import android.app.Application
import com.jesil.card.domain.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CardApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CardApplication)
            modules(
                listOf(
                    roomModule
                )
            )
        }
    }
}