package com.myapplication.io.retrofit

import com.myapplication.io.models.response.TheMovieDBResponse
import com.myapplication.io.models.response.TheMovieDBVideoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBService {

    @GET("3/{type}/{endPoint}")
    fun getMoviesOrSeries(
            @Path("type") type: String,
            @Path("endPoint") endPoint: String,
            @Query("api_key") apiKey: String
    ): Call<TheMovieDBResponse>

    @GET("3/{type}/{id}/{endPoint}")
    fun getVideo(
        @Path("type") type: String,
        @Path("id") id: Int,
        @Path("endPoint") endPoint: String,
        @Query("api_key") apiKey: String
    ): Call<TheMovieDBVideoResponse>

}