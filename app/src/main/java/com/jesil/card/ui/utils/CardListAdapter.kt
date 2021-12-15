package com.jesil.card.ui.utils

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jesil.card.R
import com.jesil.card.data.dto.Card
import com.jesil.card.databinding.CardLayoutBinding
import com.jesil.card.ui.utils.CardConstants.MASTER_CARD_NUMBER
import com.jesil.card.ui.utils.CardConstants.VISA_CARD_NUMBER

class CardListAdapter :
    ListAdapter<Card, CardListAdapter.CardListViewHolder>(CardDiffUtilCallback<Card>()) {

    class CardListViewHolder(private val binding: CardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cardItem: Card) = with(binding) {
            atmCardNumber.text = cardItem.cardNumber

            if (cardItem.cardNumber.take(1) == MASTER_CARD_NUMBER)
                masterCard()
            else
                otherCard()
            if (cardItem.cardNumber.take(2) == VISA_CARD_NUMBER)
                visaCard()

            atmCardCardName.text = cardItem.cardHolderName
            atmCardExpiringDate.text = cardItem.cardExpiringDate
        }

        private fun masterCard() = with(binding) {
            atmCardType loadCardType R.drawable.atm_card_debit_1
            cardBackground.setBackgroundResource(R.color.card_third)
        }

        private fun visaCard() = with(binding) {
            atmCardType loadCardType R.drawable.atm_card_debit_2
            cardBackground.setBackgroundResource(R.color.card_second)
        }

        private fun otherCard() = with(binding) {
            atmCardType loadCardType R.drawable.credit_card_drawable
            cardBackground.setBackgroundResource(R.color.card_first)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListViewHolder {
        return CardListViewHolder(
            CardLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardListViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class CardDiffUtilCallback<T> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }
}

