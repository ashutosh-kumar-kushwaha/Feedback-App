package me.ashutoshkk.feedbackapp.domain.model

import me.ashutoshkk.feedbackapp.data.remote.dto.FeedbackItemDto

data class FeedbackCategory(
    val category: Category,
    val feedbackItems: List<FeedbackItem>,
    var isOpen: Boolean = false
)