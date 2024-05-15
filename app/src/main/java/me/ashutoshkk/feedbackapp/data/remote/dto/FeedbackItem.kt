package me.ashutoshkk.feedbackapp.data.remote.dto

data class FeedbackItem(
    val aspect: String,
    val didWell: List<String>,
    val scopeOfImprovement: List<String>
)