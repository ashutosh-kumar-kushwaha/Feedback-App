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
import me.ashutoshkk.feedbackapp.domain.model.Feedback
import me.ashutoshkk.feedbackapp.presentation.adapters.FeedbackCategoryAdapter
import me.ashutoshkk.feedbackapp.presentation.adapters.FeedbackSpacingItemDecoration

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var feedbackCategoryAdapter: FeedbackCategoryAdapter

    private lateinit var optionBottomSheet: OptionBottomSheet

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
            feedbackCategoryAdapter = FeedbackCategoryAdapter(it.toMutableList()){ feedback, i, j ->

                val list = feedbackCategoryAdapter.list
                when(feedback){
                    Feedback.DID_WELL -> {
                        if(list[i].feedbackItems[j].didWell.size > 1){
                            optionBottomSheet = OptionBottomSheet(list[i].feedbackItems[j].didWell){
                                feedbackCategoryAdapter.list[i].feedbackItems[j].didWell = it
//                                feedbackCategoryAdapter.notifyItemChanged(i)
                            }
                            optionBottomSheet.show(parentFragmentManager, "BottomSheet")
                        }
                    }
                    Feedback.SCOPE_OF_IMPROVEMENT -> {
                        if(list[i].feedbackItems[j].scopeOfImprovement.size > 1){
                            optionBottomSheet = OptionBottomSheet(list[i].feedbackItems[j].scopeOfImprovement){
                                feedbackCategoryAdapter.list[i].feedbackItems[j].scopeOfImprovement = it
//                                feedbackCategoryAdapter.notifyItemChanged(i)
                            }
                            optionBottomSheet.show(parentFragmentManager, "BottomSheet")
                        }
                    }
                    Feedback.NONE -> {}
                }
            }
            binding.rvFeedback.adapter = feedbackCategoryAdapter
        }

    }
}