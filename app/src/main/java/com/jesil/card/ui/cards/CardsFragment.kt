package com.jesil.card.ui.cards

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jesil.card.R
import com.jesil.card.data.dto.Card
import com.jesil.card.databinding.CardsFragmentBinding
import com.jesil.card.ui.add_card.AddCardFragment
import com.jesil.card.ui.utils.CardListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardsFragment : Fragment(R.layout.cards_fragment) {
    private var _binding : CardsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<CardsViewModel>()
    private val cardAdapter by lazy {
        CardListAdapter()
    }
    val card = ArrayList<Card>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = CardsFragmentBinding.bind(view)
        binding.apply {
            btAddCards.setOnClickListener {
                openAddCard()
            }
            rcvCardList.adapter = cardAdapter
//            cardAdapter.submitList(card)
//            card.addAll(
//                listOf(
//                    Card("Jesil Toborowei", "**** **** **** 4567", "20/19", 123),
//                    Card("Bio Joyce", "5559 4334 5673 4567", "20/43", null),
//                    Card("Owei Robin", "**** **** **** 4567", "20/13", 123),
//                    Card("Success Hitti", "4965 4334 5673 4567", "20/21", null),
//                )
//
//            )
        }
    }

    private fun openAddCard() {
        val modalBottomSheet = AddCardFragment()
        modalBottomSheet.show(parentFragmentManager, AddCardFragment.TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}