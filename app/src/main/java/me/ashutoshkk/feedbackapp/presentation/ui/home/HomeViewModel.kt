package me.ashutoshkk.feedbackapp.presentation.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import me.ashutoshkk.feedbackapp.common.Resource
import me.ashutoshkk.feedbackapp.domain.model.Feedback
import me.ashutoshkk.feedbackapp.domain.model.FeedbackCategory
import me.ashutoshkk.feedbackapp.domain.model.FeedbackItem
import me.ashutoshkk.feedbackapp.domain.useCase.GetFeedbackDataUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getFeedbackDataUseCase: GetFeedbackDataUseCase) :
    ViewModel() {

    private val _feedbackCategories: MutableStateFlow<List<FeedbackCategory>> = MutableStateFlow(
        emptyList()
    )
    val feedbackCategories: StateFlow<List<FeedbackCategory>> get() = _feedbackCategories.asStateFlow()

    val live: MutableLiveData<List<FeedbackCategory>> = MutableLiveData()

    private lateinit var feedbackData: MutableList<FeedbackCategory>

    init {
        getFeedbackData()
    }

    private fun getFeedbackData() {
        getFeedbackDataUseCase().onEach {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    feedbackData = it.data!!.toMutableList()
                    _feedbackCategories.value = feedbackData
                    live.postValue(feedbackData)
                }

                is Resource.Error -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun onFeedbackChanged(feedback: Feedback, i: Int, j: Int) {
        when(feedback){
            Feedback.DID_WELL -> {
                if(feedbackData[i].feedbackItems[j].didWell.size > 1){

                }
            }
            Feedback.SCOPE_OF_IMPROVEMENT -> {
                if(feedbackData[i].feedbackItems[j].scopeOfImprovement.size > 1){

                }
            }
            Feedback.NONE -> {}
        }
    }
}