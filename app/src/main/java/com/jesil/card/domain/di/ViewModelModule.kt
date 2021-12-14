package com.jesil.card.domain.di

import com.jesil.card.ui.add_card.AddCardViewModel
import com.jesil.card.ui.cards.CardsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { CardsViewModel(get()) }

    viewModel { AddCardViewModel(get()) }

}