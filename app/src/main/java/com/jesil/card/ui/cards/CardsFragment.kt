package com.jesil.card.ui.cards

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jesil.card.R
import com.jesil.card.data.dto.Card
import com.jesil.card.databinding.CardsFragmentBinding
import com.jesil.card.ui.add_card.AddCardFragment
import com.jesil.card.ui.utils.CardListAdapter
import com.jesil.card.ui.utils.DataResult.Success
import com.jesil.card.ui.utils.DataResult.Failure
import com.jesil.card.ui.utils.DataResult.Loading
import com.jesil.card.ui.utils.messageToUser
import com.jesil.card.ui.utils.state
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardsFragment : Fragment(R.layout.cards_fragment) {
    private var _binding: CardsFragmentBinding? = null
    private val binding get() = _binding!!
    private val cardsViewModel: CardsViewModel by viewModel()
    private val cardAdapter by lazy {
        CardListAdapter()
    }

    override fun onResume() {
        super.onResume()
        getAllCards()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = CardsFragmentBinding.bind(view)
        binding.apply {
            btAddCards.setOnClickListener {
                openAddCard()
            }
            rcvCardList.adapter = cardAdapter
        }
        getAllCards()
    }

    private fun getAllCards() {
        cardsViewModel.cards().observe(viewLifecycleOwner) { card ->
            when (card) {
                is Success -> {
                    successState(card.data)
                }
                is Failure -> {
                    failureState(card.throwable?.message)
                }
                is Loading -> {
                    loadingState()
                }
            }
        }
    }

    private fun openAddCard() {
        val modalBottomSheet = AddCardFragment()
        modalBottomSheet.show(parentFragmentManager, AddCardFragment.TAG)
    }

    private fun loadingState() = with(binding) {
        pbLoading state true
    }

    private fun failureState(message: String?) = with(binding) {
        cardCoordinatorLayout messageToUser message
        pbLoading state false

    }

    private fun successState(cards: List<Card>?) = with(binding) {
        if (cards.isNullOrEmpty()) {
            emptyGroup state true
            pbLoading state false
        } else {
            cardAdapter.apply {
                submitList(cards)
                notifyItemInserted(0)
            }
            btAddCards state true
            pbLoading state false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
