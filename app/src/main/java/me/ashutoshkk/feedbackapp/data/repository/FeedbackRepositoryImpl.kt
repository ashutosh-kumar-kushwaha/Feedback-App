package me.ashutoshkk.feedbackapp.data.repository

import me.ashutoshkk.feedbackapp.data.remote.FeedbackApi
import me.ashutoshkk.feedbackapp.data.remote.dto.FeedbackDataDto
import me.ashutoshkk.feedbackapp.domain.repository.FeedbackRepository
import javax.inject.Inject

class FeedbackRepositoryImpl @Inject constructor(private val feedbackApi: FeedbackApi): FeedbackRepository {
    override suspend fun getFeedbackData(): FeedbackDataDto {
        return feedbackApi.getFeedbackData()
    }
}