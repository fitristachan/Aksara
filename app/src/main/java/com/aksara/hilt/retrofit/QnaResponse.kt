package com.aksara.hilt.retrofit

import com.google.gson.annotations.SerializedName

data class QnaResponse(

    @field:SerializedName("answer")
    val answer: String,

    @field:SerializedName("end")
    val end: Int,

    @field:SerializedName("score")
    val score: Double,

    @field:SerializedName("start")
    val start: Int
)

data class ApiErrorResponse(
    @field:SerializedName("error")
    val error: String
)
