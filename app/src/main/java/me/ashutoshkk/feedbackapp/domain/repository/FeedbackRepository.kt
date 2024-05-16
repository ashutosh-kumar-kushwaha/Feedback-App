package me.ashutoshkk.feedbackapp.domain.repository

import me.ashutoshkk.feedbackapp.data.remote.dto.FeedbackDataDto

interface FeedbackRepository {
    suspend fun getFeedbackData(): FeedbackDataDto
}