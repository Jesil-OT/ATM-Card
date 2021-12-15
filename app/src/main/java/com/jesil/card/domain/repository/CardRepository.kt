package com.jesil.card.domain.repository

import com.jesil.card.data.dto.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun addCard(card: Card)

    fun getAllCard(): Flow<List<Card>>

    suspend fun deleteCard(card: Card)
}
