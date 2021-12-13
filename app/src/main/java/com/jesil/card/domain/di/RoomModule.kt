package com.jesil.card.domain.di

import android.content.Context
import androidx.room.Room
import com.jesil.card.data.local.CardDao
import com.jesil.card.data.local.CardDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {
    single { createDatabase(androidApplication()) }
    single { createCardDao(get()) }
}

fun createDatabase(context: Context): CardDatabase {
    return Room.databaseBuilder(context, CardDatabase::class.java, "card_database")
        .fallbackToDestructiveMigration()
        .build()
}

fun createCardDao(cardDatabase: CardDatabase): CardDao {
    return cardDatabase.cardDao()
}
