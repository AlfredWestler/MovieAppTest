package com.myapplication.ui.splash

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.io.models.response.Results
import com.myapplication.io.models.response.TheMovieDBResponse
import com.myapplication.io.models.sealed.KindResponse
import com.myapplication.io.repository.MainRepository
import com.myapplication.io.room.entities.CurrentMoviesEntity
import com.myapplication.io.room.entities.CurrentShowsEntity
import com.myapplication.io.room.entities.PopularMoviesEntity
import com.myapplication.io.room.entities.PopularShowsEntity
import com.myapplication.utils.Mapper
import com.myapplication.utils.Utilities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository, private val application: Application) : ViewModel(){

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _popularMovies = MutableLiveData<List<Results>>()
    val popularMovies: LiveData<List<Results>> get() = _popularMovies

    private val _popularShows = MutableLiveData<List<Results>>()
    val popularShows: LiveData<List<Results>> get() = _popularShows

    private val _currentMovies = MutableLiveData<List<Results>>()
    val currentMovies: LiveData<List<Results>> get() = _currentMovies

    private val _currentShows = MutableLiveData<List<Results>>()
    val currentShows: LiveData<List<Results>> get() = _currentShows

    private val _networkState = MutableLiveData<Boolean>()
    val networkState: LiveData<Boolean> get() = _networkState

    fun getAllInfo(){
        if(Utilities.internetConnection(application)){
            _networkState.value = true
            viewModelScope.launch(Dispatchers.IO){
                repository.getPopularMovies {
                    when(it){
                        is KindResponse.OnError -> _errorMessage.value = it.errorMessage
                        is KindResponse.OnSuccess -> _popularMovies.value = it.data.results
                    }
                }
                repository.getPopularShows {
                    when(it){
                        is KindResponse.OnError -> _errorMessage.value = it.errorMessage
                        is KindResponse.OnSuccess -> _popularShows.value = it.data.results
                    }
                }
                repository.getCurrentMovies {
                    when(it){
                        is KindResponse.OnError -> _errorMessage.value = it.errorMessage
                        is KindResponse.OnSuccess -> _currentMovies.value = it.data.results
                    }
                }
                repository.getCurrentShows {
                    when(it){
                        is KindResponse.OnError -> _errorMessage.value = it.errorMessage
                        is KindResponse.OnSuccess -> _currentShows.value = it.data.results
                    }
                }
            }
        }else{
            _networkState.value = false
        }
    }

    fun getPopularMoviesEntity(): LiveData<List<PopularMoviesEntity>>{
        return repository.getPopularMoviesDB()
    }

    fun getPopularShowsEntity(): LiveData<List<PopularShowsEntity>>{
        return repository.getPopularShowsDB()
    }

    fun getCurrentMoviesEntity(): LiveData<List<CurrentMoviesEntity>>{
        return repository.getCurrentMoviesDB()
    }

    fun getCurrentShowsEntity(): LiveData<List<CurrentShowsEntity>>{
        return repository.getCurrentShowsDB()
    }
}