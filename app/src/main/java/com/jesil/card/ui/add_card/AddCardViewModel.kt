package com.jesil.card.ui.add_card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesil.card.data.dto.Card
import com.jesil.card.domain.repository.CardRepository
import com.jesil.card.ui.utils.DataResult
import kotlinx.coroutines.launch

class AddCardViewModel(
    private val cardRepo: CardRepository
): ViewModel() {

    private val addCardUI = MutableLiveData<DataResult<Card>>()
    private val deleteCardUI = MutableLiveData<DataResult<Card>>()

    fun addCard(card: Card): LiveData<DataResult<Card>>{
        doAddCard(card)
        return addCardUI
    }

    fun deleteCard(card: Card): LiveData<DataResult<Card>>{
        doDeleteCard(card)
        return deleteCardUI
    }

    private fun doDeleteCard(card: Card) {
        viewModelScope.launch {
            cardRepo.deleteCard(card)
        }
    }

    private fun doAddCard(card: Card) {
        viewModelScope.launch {
            cardRepo.addCard(card)
        }
    }

}