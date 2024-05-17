package me.ashutoshkk.feedbackapp.data.remote.dto

import me.ashutoshkk.feedbackapp.domain.model.FeedbackItem
import me.ashutoshkk.feedbackapp.domain.model.Option

data class FeedbackItemDto(
    val aspect: String,
    val didWell: List<String>,
    val scopeOfImprovement: List<String>
)

fun FeedbackItemDto.toFeedbackItem() = FeedbackItem(
    aspect = aspect,
    didWell = didWell.map {
        it.toOption()
    },
    scopeOfImprovement = scopeOfImprovement.map {
        it.toOption()
    }
)

fun String.toOption(): Option = Option(this)