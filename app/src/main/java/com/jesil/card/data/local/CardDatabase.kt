package com.jesil.card.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jesil.card.data.dto.Card

@Database(entities = [Card::class], version = 1, exportSchema = false)
abstract class CardDatabase: RoomDatabase() {

    abstract fun cardDao() : CardDao

    companion object {
        @Volatile private var INSTANCE: CardDatabase? = null
        fun getCardDatabase(context: Context): CardDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext, CardDatabase::class.java, "card_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }
}