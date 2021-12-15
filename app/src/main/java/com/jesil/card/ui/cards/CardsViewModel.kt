package com.jesil.card.ui.cards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesil.card.data.dto.Card
import com.jesil.card.domain.repository.CardRepository
import com.jesil.card.ui.utils.DataResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CardsViewModel(
    private val cardRepo: CardRepository
) : ViewModel() {

    private val cardsUI = MutableLiveData<List<Card>>()

    fun cards(): LiveData<List<Card>> {
        doCards()
        return cardsUI
    }

    fun deleteCard(card: Card) = viewModelScope.launch {
        cardRepo.deleteCard(card)
    }

    private fun doCards() = viewModelScope.launch {
        cardRepo.getAllCard().collect{
            cardsUI.postValue(it)
        }
    }

}