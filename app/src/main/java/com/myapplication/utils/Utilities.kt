package com.myapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.myapplication.io.models.response.TheMovieDBResponse
import com.myapplication.io.models.response.TheMovieDBVideoResponse
import com.myapplication.io.retrofit.TheMovieDBService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Utilities {

    fun internetConnection(context: Context): Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }

    fun serviceConsume(service: TheMovieDBService, type: String, endPoint: String, apiKey: String, results:(isError: Boolean, message: String?, dataOut: TheMovieDBResponse?)->Unit){
        val call = service.getMoviesOrSeries(type, endPoint, apiKey)
        call.enqueue(object: Callback<TheMovieDBResponse> {
            override fun onResponse(call: Call<TheMovieDBResponse>, response: Response<TheMovieDBResponse>) {
                Log.e("service", "$type/$endPoint")
                Log.e("code", "${response.code()}")
                Log.e("body", "${response.body()}")
                if (response.code() == 200) {
                    response.body()?.let {
                        results.invoke(false, null, it)
                    } ?: run {
                        results.invoke(true, "empty body response", null)
                    }
                } else {
                    results.invoke(true, response.body()?.status_message?: "something went wrong", null)
                }
            }

            override fun onFailure(call: Call<TheMovieDBResponse>, t: Throwable) {
                results.invoke(true, t.message, null)
                call.cancel()
            }
        })
    }

    fun serviceConsumeVideo(service: TheMovieDBService, type: String, endPoint: String, apiKey: String, id: Int, results:(isError: Boolean, message: String?, dataOut: TheMovieDBVideoResponse?)->Unit){
        val call = service.getVideo(type, id, endPoint, apiKey)
        call.enqueue(object: Callback<TheMovieDBVideoResponse> {
            override fun onResponse(call: Call<TheMovieDBVideoResponse>, response: Response<TheMovieDBVideoResponse>) {
                Log.e("service", "$type/$endPoint")
                Log.e("code", "${response.code()}")
                Log.e("body", "${response.body()}")
                if (response.code() == 200) {
                    response.body()?.let {
                        results.invoke(false, null, it)
                    } ?: run {
                        results.invoke(true, "empty body response", null)
                    }
                } else {
                    results.invoke(true, "something went wrong", null)
                }
            }

            override fun onFailure(call: Call<TheMovieDBVideoResponse>, t: Throwable) {
                results.invoke(true, t.message, null)
                call.cancel()
            }
        })
    }
}