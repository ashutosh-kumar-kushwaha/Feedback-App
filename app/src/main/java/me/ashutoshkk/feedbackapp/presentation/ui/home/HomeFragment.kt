package me.ashutoshkk.feedbackapp.presentation.ui.home

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.ashutoshkk.feedbackapp.R
import me.ashutoshkk.feedbackapp.databinding.FragmentHomeBinding
import me.ashutoshkk.feedbackapp.presentation.adapters.FeedbackCategoryAdapter
import me.ashutoshkk.feedbackapp.presentation.adapters.FeedbackSpacingItemDecoration

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var feedbackCategoryAdapter: FeedbackCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.live.observe(viewLifecycleOwner) {
            Log.d("Ashu", it.toString())
            feedbackCategoryAdapter = FeedbackCategoryAdapter(it.toMutableList())
            binding.rvFeedback.adapter = feedbackCategoryAdapter
        }

    }
}