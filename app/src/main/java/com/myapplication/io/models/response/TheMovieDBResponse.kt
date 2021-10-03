package com.myapplication.io.models.response

import com.google.gson.annotations.SerializedName

data class TheMovieDBResponse(
        @SerializedName("dates") val dates : Dates,
        @SerializedName("page") val page : Int,
        @SerializedName("results") val results : List<Results>,
        @SerializedName("total_pages") val total_pages : Int,
        @SerializedName("total_results") val total_results : Int,
        @SerializedName("status_message")val status_message: String,
        @SerializedName("status_code")val status_code: Int,
        @SerializedName("success")val success: Boolean
)
