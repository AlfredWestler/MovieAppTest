package com.myapplication.io.repository

import com.myapplication.io.models.enums.EndPoints
import com.myapplication.io.models.sealed.VideoKindResponse
import com.myapplication.io.retrofit.TheMovieDBService
import com.myapplication.utils.Utilities
import javax.inject.Inject

class DetailRepository @Inject constructor(private val service: TheMovieDBService, private val apiKey: String) {

    fun getVideo(type: String, id: Int, response: (data: VideoKindResponse)->Unit){
        Utilities.serviceConsumeVideo(service,type,EndPoints.VIDEOS.value,apiKey,id){isError, message, dataOut ->
            if(isError) response.invoke(VideoKindResponse.OnError(message!!))
            else response.invoke(VideoKindResponse.OnSuccess(dataOut!!))
        }
    }
}