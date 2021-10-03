package com.myapplication.io.models.sealed

import com.myapplication.io.models.response.TheMovieDBResponse

sealed class KindResponse{
    class OnSuccess(val data: TheMovieDBResponse): KindResponse()
    class OnError(val errorMessage: String): KindResponse()
}
