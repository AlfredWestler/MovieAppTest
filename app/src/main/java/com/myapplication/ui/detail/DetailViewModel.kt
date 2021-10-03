package com.myapplication.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myapplication.io.models.response.TheMovieDBVideoResponse
import com.myapplication.io.models.sealed.VideoKindResponse
import com.myapplication.io.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class DetailViewModel (application: Application): AndroidViewModel(application){

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _videos = MutableLiveData<TheMovieDBVideoResponse>()
    val videos: LiveData<TheMovieDBVideoResponse> get() = _videos

    fun getVideo(repository: DetailRepository, type: String, id: Int){
        repository.getVideo(type, id){
            when(it){
                is VideoKindResponse.OnError -> _errorMessage.value = it.message
                is VideoKindResponse.OnSuccess -> _videos.value = it.data
            }
        }
    }
}