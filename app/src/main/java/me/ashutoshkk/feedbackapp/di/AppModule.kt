package me.ashutoshkk.feedbackapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.ashutoshkk.feedbackapp.common.Constants.BASE_URL
import me.ashutoshkk.feedbackapp.data.remote.FeedbackApi
import me.ashutoshkk.feedbackapp.data.repository.FeedbackRepositoryImpl
import me.ashutoshkk.feedbackapp.domain.repository.FeedbackRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun providesRetrofit() =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providesFeedbackApi(retrofit: Retrofit) = retrofit.create(FeedbackApi::class.java)

    @Provides
    fun repo(feedbackApi: FeedbackApi): FeedbackRepository = FeedbackRepositoryImpl(feedbackApi)

}