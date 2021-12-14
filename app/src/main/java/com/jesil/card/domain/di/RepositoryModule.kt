package com.jesil.card.domain.di

import com.jesil.card.domain.repository.CardRepository
import com.jesil.card.domain.repository.CardRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    // single instance of CardRepository
    single<CardRepository> { CardRepositoryImpl(get()) }
    factory { CardRepositoryImpl(get()) }

}





