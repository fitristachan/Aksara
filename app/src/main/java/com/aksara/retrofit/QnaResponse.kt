package com.aksara.retrofit

import com.google.gson.annotations.SerializedName

data class QnaResponse(

    @field:SerializedName("answer")
    val answer: String,

    @field:SerializedName("end")
    val end: Int,

    @field:SerializedName("score")
    val score: Float,

    @field:SerializedName("start")
    val start: Int
)