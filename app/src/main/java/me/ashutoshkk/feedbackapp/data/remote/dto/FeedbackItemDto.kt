package me.ashutoshkk.feedbackapp.data.remote.dto

import me.ashutoshkk.feedbackapp.domain.model.FeedbackItem

data class FeedbackItemDto(
    val aspect: String,
    val didWell: List<String>,
    val scopeOfImprovement: List<String>
)

fun FeedbackItemDto.toFeedbackItem() = FeedbackItem(
    aspect = aspect,
    didWell = didWell,
    scopeOfImprovement = scopeOfImprovement
)