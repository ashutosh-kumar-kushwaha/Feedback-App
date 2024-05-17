package me.ashutoshkk.feedbackapp.presentation.ui.thankYou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import me.ashutoshkk.feedbackapp.R
import me.ashutoshkk.feedbackapp.databinding.FragmentThankYouBinding

class ThankYouFragment : Fragment() {

    private var _binding: FragmentThankYouBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThankYouBinding.inflate(inflater, container, false)

        binding.btnDashboard.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }
}