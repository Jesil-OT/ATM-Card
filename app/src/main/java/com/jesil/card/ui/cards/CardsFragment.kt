package com.jesil.card.ui.cards

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jesil.card.R
import com.jesil.card.databinding.CardsFragmentBinding
import com.jesil.card.ui.add_card.AddCardFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardsFragment : Fragment(R.layout.cards_fragment) {
    private var _binding : CardsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<CardsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = CardsFragmentBinding.bind(view)
        binding.apply {
            btAddCards.setOnClickListener {
                openAddCard()
            }
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