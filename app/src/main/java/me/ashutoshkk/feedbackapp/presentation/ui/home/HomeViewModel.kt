package me.ashutoshkk.feedbackapp.presentation.ui.home

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
import me.ashutoshkk.feedbackapp.domain.useCase.GetFeedbackDataUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getFeedbackDataUseCase: GetFeedbackDataUseCase) :
    ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        getFeedbackData()
    }

    private fun getFeedbackData() {
        getFeedbackDataUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    _uiState.value = UiState(categories = it.data!!)
                }

                is Resource.Error -> {
                    _uiState.update { uiState ->
                        uiState.copy(error = it.message)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}