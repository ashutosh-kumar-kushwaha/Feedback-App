package me.ashutoshkk.feedbackapp.domain.model

import me.ashutoshkk.feedbackapp.presentation.adapters.Feedback

data class FeedbackItem(
    val aspect: String,
    val didWell: List<String>,
    val scopeOfImprovement: List<String>,
    val selectedFeedback: Feedback = Feedback.NONE
)