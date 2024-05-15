package me.ashutoshkk.feedbackapp.data.remote.dto

data class FeedbackDataDto(
    val feedbackCategories: List<FeedbackCategory>,
    val statusCode: Int
)