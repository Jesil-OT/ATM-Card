package com.jesil.card.ui.cards

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.jesil.card.R
import com.jesil.card.data.dto.Card
import com.jesil.card.databinding.CardsFragmentBinding
import com.jesil.card.ui.add_card.AddCardFragment
import com.jesil.card.ui.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardsFragment : Fragment(R.layout.cards_fragment) {
    private var _binding: CardsFragmentBinding? = null
    private val binding get() = _binding!!
    private val cardsViewModel: CardsViewModel by viewModel()
    private val cardAdapter by lazy {
        CardListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllCards()
        _binding = CardsFragmentBinding.bind(view)
        binding.apply {
            btAddCards.setOnClickListener {
                openAddCard()
            }
            rcvCardList.apply {
                adapter = cardAdapter
                setHasFixedSize(true)
            }
            val swipeHandler = object : CardSwipeToDelete(requireContext()) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val card = cardAdapter.currentList[viewHolder.adapterPosition]
                    deleteCard(card)
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(rcvCardList)

        }
    }

    private fun deleteCard(card: Card) = with(binding){
        cardsViewModel.deleteCard(card)
        cardCoordinatorLayout messageToUser "Card Deleted Successfully"
    }

    private fun getAllCards() {
        cardsViewModel.cards().observe(viewLifecycleOwner) { card ->
            doGetAllCards(card)
        }
    }

    private fun openAddCard() {
        val modalBottomSheet = AddCardFragment()
        modalBottomSheet.show(parentFragmentManager, AddCardFragment.TAG)
    }

    private fun doGetAllCards(cards: List<Card>?) = with(binding) {
        if (cards.isNullOrEmpty()) {
            emptyGroup state true
        } else {
            cardAdapter.apply {
                submitList(cards)
                notifyItemInserted(cards.lastIndex)
            }
            btAddCards state true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
