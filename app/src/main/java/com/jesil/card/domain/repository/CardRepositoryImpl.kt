package com.jesil.card.domain.repository

import com.jesil.card.data.dto.Card
import com.jesil.card.data.local.CardDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardRepositoryImpl(
    private val cardDao: CardDao
) : CardRepository {
    override suspend fun addCard(card: Card) {
        cardDao.insertCard(card = card)
    }

    override suspend fun getAllCard(): Flow<List<Card>> {
        return flow {
            emit(cardDao.getAllCard())
        }
    }

    override suspend fun deleteCard(card: Card) {
        cardDao.deleteCard(card)
    }

}