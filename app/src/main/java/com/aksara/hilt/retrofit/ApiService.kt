package com.aksara.hilt.retrofit

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("qna")
    suspend fun getAnswer(
        @Query("context") context: String,
        @Query("question") question: String
    ): QnaResponse
}