package me.ashutoshkk.feedbackapp.data.remote.dto

data class FeedbackCategory(
    val categoryName: String,
    val feedbackItems: List<FeedbackItemDto>
)