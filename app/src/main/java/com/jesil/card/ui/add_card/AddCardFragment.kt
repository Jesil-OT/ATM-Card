package com.jesil.card.ui.add_card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jesil.card.data.dto.Card
import com.jesil.card.databinding.AddCardFragmentBinding
import com.jesil.card.ui.utils.DataResult.Loading
import com.jesil.card.ui.utils.DataResult.Failure
import com.jesil.card.ui.utils.DataResult.Success
import com.jesil.card.ui.utils.messageToUser
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [AddCardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddCardFragment : BottomSheetDialogFragment() {
    private var _binding: AddCardFragmentBinding? = null
    private val binding get() = _binding!!
    private val addCardViewModel: AddCardViewModel by viewModel()

    companion object {
        const val TAG = "AddCardFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddCardFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            btAddCard.setOnClickListener {
                doAddCard()
                dismiss()
            }
        }
        return binding.root
    }

    private fun doAddCard() = with(binding) {
        val cardName = etCardName.text.toString()
        val cardNumber = etCardNumber.text.toString()
        val cardExpiryDate = etCardExpiring.text.toString()
        val cardCvv = etCardCvv.text.toString()

        val card = Card(
            cardHolderName = cardName,
            cardNumber = cardNumber,
            cardExpiringDate = cardExpiryDate,
            cardCvv = cardCvv
        )

        addCard(card)

    }

    private fun addCard(card: Card) {
        addCardViewModel.addCard(card).observe(viewLifecycleOwner) { card ->
            when (card) {
                is Loading -> {
                    loadingState()
                }
                is Success -> {
                    successState()
                }
                is Failure -> {
                    failureState()
                }
            }
        }
    }

    private fun successState() = with(binding) {
        addCardLayout messageToUser "Card was added Successfully"
    }

    private fun failureState() = with(binding){
        addCardLayout messageToUser "Card was not added"
    }

    private fun loadingState() = with(binding){
        addCardLayout messageToUser "Loading..."
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}