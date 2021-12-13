package com.jesil.card.data.local

import androidx.room.*
import com.jesil.card.data.dto.Card
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDto {

    @Query("SELECT * FROM card_table")
    fun getAllCard(): Flow<List<Card>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCard(card: Card)

    @Delete
    fun deleteCard(card: Card)

}