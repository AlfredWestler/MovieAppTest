package com.myapplication.io.retrofit

import com.myapplication.io.models.enums.EndPoints
import com.myapplication.io.models.enums.Type
import com.myapplication.utils.Utilities
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val baseUrl = "https://api.themoviedb.org/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    val service: TheMovieDBService = getRetrofit().create(TheMovieDBService::class.java)

}

fun main(){
    /*Utilities().serviceConsume(Type.TV_SHOWS.value, EndPoints.MOST_POPULAR.value, "4313691a974e9f3ddfcf21b4b330aa53"){ isError, message, dataOut ->
        if(isError) println("an error has occurred: $message")
        else println("response: $dataOut")
    }*/

}