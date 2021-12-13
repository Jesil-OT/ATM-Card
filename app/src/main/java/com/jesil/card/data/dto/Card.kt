package com.jesil.card.data

data class Card(
    val cardHolderName: String,
    val cardNumber: String,
    val cardExpiringDate : String,
    val cardCvv: Int?
)