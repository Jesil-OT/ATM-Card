package com.jesil.card.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jesil.card.data.dto.Card

@Database(entities = [Card::class], version = 1, exportSchema = false)
abstract class CardDatabase: RoomDatabase() {

    abstract fun cardDao() : CardDao

}