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

    private val cardsUI = MutableLiveData<DataResult<Card>>()

    fun cards(): LiveData<DataResult<Card>>{
        doCards()
        return cardsUI
    }

    private fun doCards() {
        viewModelScope.launch {
            cardRepo.getAllCard().onStart {
                cardsUI.postValue(DataResult.Loading)
            }.catch {
                cardsUI.postValue(DataResult.Failure(it.cause))
            }.collect {
                cardsUI.postValue(DataResult.Success(it))
            }
        }
    }
}