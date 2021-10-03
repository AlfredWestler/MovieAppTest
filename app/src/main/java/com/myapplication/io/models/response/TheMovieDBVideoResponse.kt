package com.myapplication.io.models.response

import com.google.gson.annotations.SerializedName

data class TheMovieDBVideoResponse(
    @SerializedName("id") val id : Int,
    @SerializedName("results") val results : List<VideoResults>,
)

data class VideoResults(
    @SerializedName("key") val key : String,
)