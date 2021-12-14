package com.jesil.card.domain.di

import android.content.Context
import com.jesil.card.data.local.CardDao
import com.jesil.card.data.local.CardDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    fun createDatabase(context: Context): CardDatabase {
        return CardDatabase.getCardDatabase(context)
    }

    fun provideCardDao(cardDatabase: CardDatabase): CardDao {
        return cardDatabase.cardDao()
    }

    single { createDatabase(androidContext()) }
    single { provideCardDao( get()) }
}


