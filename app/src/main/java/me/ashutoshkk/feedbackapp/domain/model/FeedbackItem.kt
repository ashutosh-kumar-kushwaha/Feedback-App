package me.ashutoshkk.feedbackapp.domain.model

data class FeedbackItem(
    val aspect: String,
    var didWell: List<Option>,
    var scopeOfImprovement: List<Option>,
    var selectedFeedback: Feedback = Feedback.NONE
)