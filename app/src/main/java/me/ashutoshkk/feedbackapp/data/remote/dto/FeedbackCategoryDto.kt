package me.ashutoshkk.feedbackapp.data.remote.dto

data class FeedbackCategoryDto(
    val categoryName: String,
    val feedbackItems: List<FeedbackItemDto>
)