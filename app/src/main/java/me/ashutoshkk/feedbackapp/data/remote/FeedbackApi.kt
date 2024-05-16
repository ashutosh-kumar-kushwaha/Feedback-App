package me.ashutoshkk.feedbackapp.data.remote

import me.ashutoshkk.feedbackapp.data.remote.dto.FeedbackDataDto
import retrofit2.http.GET

interface FeedbackApi {

    @GET("api/rating/getFeedbackData")
    suspend fun getFeedbackData() : FeedbackDataDto

}