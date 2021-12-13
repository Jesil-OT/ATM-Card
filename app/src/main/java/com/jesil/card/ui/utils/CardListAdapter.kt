package com.jesil.card.ui.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jesil.card.R
import com.jesil.card.data.dto.Card
import com.jesil.card.databinding.CardLayoutBinding

class CardListAdapter :
    ListAdapter<Card, CardListAdapter.CardListViewHolder>(CardDiffUtilCallback<Card>()) {

    class CardListViewHolder(private val binding: CardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cardItem: Card) = with(binding) {
            atmCardNumber.text = cardItem.cardNumber

            when (cardItem.cardNumber.take(2)) {
                "55" -> { masterCard() }
                "49" -> { visaCard() }
                else -> { otherCard() }
            }

            atmCardCardName.text = cardItem.cardHolderName
            atmCardExpiringDate.text = cardItem.cardExpiringDate
        }

        private fun masterCard() = with(binding) {
            atmCardType loadCardType R.drawable.atm_card_debit_1
        }

        @SuppressLint("ResourceAsColor")
        private fun visaCard() = with(binding) {
            atmCardType loadCardType R.drawable.atm_card_debit_2
        }

        private fun otherCard() = with(binding) {
            atmCardType loadCardType R.drawable.credit_card_drawable
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

