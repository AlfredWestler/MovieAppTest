package com.myapplication.io.models.sealed

import com.myapplication.io.models.response.TheMovieDBVideoResponse

sealed class VideoKindResponse{
    class OnError(val message: String): VideoKindResponse()
    class OnSuccess(val data: TheMovieDBVideoResponse): VideoKindResponse()
}
