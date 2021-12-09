package com.jesil.card.ui.add_card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jesil.card.R
import com.jesil.card.databinding.AddCardFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [AddCardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddCardFragment : BottomSheetDialogFragment() {
    private var _binding: AddCardFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel  by viewModel<AddCardViewModel>()

    companion object {
        const val TAG = "AddCardFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddCardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}