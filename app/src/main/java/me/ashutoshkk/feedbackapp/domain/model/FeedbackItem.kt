package me.ashutoshkk.feedbackapp.domain.model

data class FeedbackItem(
    val aspect: String,
    val didWell: List<String>,
    val scopeOfImprovement: List<String>,
    var selectedFeedback: Feedback = Feedback.NONE
)