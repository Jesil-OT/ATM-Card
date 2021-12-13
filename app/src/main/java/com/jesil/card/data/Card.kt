package com.jesil.card.data

data class Card(
    val cardHolderName: String,
    val cardNumber: Long,
    val cardExpiringDate : String,
    val cardCvv: Int?
)