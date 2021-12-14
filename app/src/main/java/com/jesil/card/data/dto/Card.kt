package com.jesil.card.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val pk: Int = 0,
    @ColumnInfo(name = "card_holder_name")
    val cardHolderName: String,
    @ColumnInfo(name = "card_number")
    val cardNumber: String,
    @ColumnInfo(name = "card_expiring_date")
    val cardExpiringDate: String,
    @ColumnInfo(name = "card_cvv")
    val cardCvv: String?
)