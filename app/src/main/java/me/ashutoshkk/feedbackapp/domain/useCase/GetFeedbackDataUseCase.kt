package me.ashutoshkk.feedbackapp.domain.useCase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.ashutoshkk.feedbackapp.common.Resource
import me.ashutoshkk.feedbackapp.data.remote.dto.toFeedbackCategory
import me.ashutoshkk.feedbackapp.domain.model.FeedbackCategory
import me.ashutoshkk.feedbackapp.domain.repository.FeedbackRepository
import retrofit2.HttpException
import javax.inject.Inject

class GetFeedbackDataUseCase @Inject constructor(private val repository: FeedbackRepository) {

    operator fun invoke(): Flow<Resource<List<FeedbackCategory>>> = flow {
        emit(Resource.Loading())
        try {
            val data = repository.getFeedbackData()
            emit(Resource.Success(data.feedbackCategories.map { it.toFeedbackCategory() }))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occurred\nResponse code: ${e.code()}"
                )
            )
            e.printStackTrace()
        } catch (e: Exception) {
            emit(Resource.Error("Couldn't reach the server\nCheck your internet connection"))
            e.printStackTrace()
        }
    }

}