package me.ashutoshkk.feedbackapp.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import me.ashutoshkk.feedbackapp.databinding.OptionBottomSheetBinding
import me.ashutoshkk.feedbackapp.domain.model.Option
import me.ashutoshkk.feedbackapp.presentation.adapters.OptionAdapter

class OptionBottomSheet(private val options: List<Option>, private val onDoneClick: (List<Option>) -> Unit): BottomSheetDialogFragment() {

    private var _binding: OptionBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        OptionAdapter(options.toMutableList())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = OptionBottomSheetBinding.inflate(inflater, container, false)

        binding.rvOptions.adapter = adapter

        binding.btnCross.setOnClickListener{
            dismiss()
        }

        binding.btnDone.setOnClickListener {
            onDoneClick(adapter.list)
            dismiss()
        }

        return binding.root
    }

}