package me.ashutoshkk.feedbackapp.data.remote.dto

import me.ashutoshkk.feedbackapp.domain.model.FeedbackCategory
import me.ashutoshkk.feedbackapp.domain.model.getCategoryFromString

data class FeedbackCategoryDto(
    val categoryName: String,
    val feedbackItems: List<FeedbackItemDto>
)

fun FeedbackCategoryDto.toFeedbackCategory() =
    FeedbackCategory(
        category = getCategoryFromString(categoryName),
        feedbackItems = feedbackItems.map {
            it.toFeedbackItem()
        }
    )
