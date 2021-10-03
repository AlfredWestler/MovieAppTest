package com.myapplication.io.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.myapplication.io.models.enums.EndPoints
import com.myapplication.io.models.enums.Type
import com.myapplication.io.models.response.Results
import com.myapplication.io.models.sealed.KindResponse
import com.myapplication.io.retrofit.TheMovieDBService
import com.myapplication.io.room.dao.MoviesShowsDao
import com.myapplication.io.room.entities.CurrentMoviesEntity
import com.myapplication.io.room.entities.CurrentShowsEntity
import com.myapplication.io.room.entities.PopularMoviesEntity
import com.myapplication.io.room.entities.PopularShowsEntity
import com.myapplication.utils.Mapper
import com.myapplication.utils.Utilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

class MainRepository @Inject constructor(private val service:TheMovieDBService, private val apiKey: String, private val theMovieDBDao: MoviesShowsDao) {

    fun getPopularMovies(data: (result: KindResponse) -> Unit) {
        Utilities.serviceConsume(
            service,
            Type.MOVIES.value,
            EndPoints.MOST_POPULAR.value,
            apiKey
        ) { isError, message, dataOut ->
            if (isError) data.invoke(KindResponse.OnError(message!!))
            else {
                data.invoke(KindResponse.OnSuccess(dataOut!!))
                GlobalScope.launch {
                    dataOut.results.forEach { addPopularMovies(it) }
                }
            }
        }
    }

    fun getPopularShows(data: (result: KindResponse) -> Unit) {
        Utilities.serviceConsume(
            service,
            Type.TV_SHOWS.value,
            EndPoints.MOST_POPULAR.value,
            apiKey
        ) { isError, message, dataOut ->
            if (isError) data.invoke(KindResponse.OnError(message!!))
            else {
                data.invoke(KindResponse.OnSuccess(dataOut!!))
                GlobalScope.launch {
                    dataOut.results.forEach { addPopularShows(it) }
                }
            }
        }
    }

    fun getCurrentMovies(data: (result: KindResponse) -> Unit) {
        Utilities.serviceConsume(
            service,
            Type.MOVIES.value,
            EndPoints.NOW_PLAYING.value,
            apiKey
        ) { isError, message, dataOut ->
            if (isError) data.invoke(KindResponse.OnError(message!!))
            else {
                data.invoke(KindResponse.OnSuccess(dataOut!!))
                GlobalScope.launch {
                    dataOut.results.forEach { addCurrentMovies(it) }
                }
            }
        }
    }

    fun getCurrentShows(data: (result: KindResponse) -> Unit) {
        Utilities.serviceConsume(
            service,
            Type.TV_SHOWS.value,
            EndPoints.ON_AIR_NOW.value,
            apiKey
        ) { isError, message, dataOut ->
            if (isError) data.invoke(KindResponse.OnError(message!!))
            else {
                data.invoke(KindResponse.OnSuccess(dataOut!!))
                GlobalScope.launch {
                    dataOut.results.forEach { addCurrentShows(it) }
                }
            }
        }
    }

    private suspend fun addPopularMovies(results: Results) {
        val movie = Mapper().transformToPopularMoviesEntity(results)
        theMovieDBDao.addPopularMovie(movie)
    }

    fun getPopularMoviesDB(): LiveData<List<PopularMoviesEntity>> {
        return theMovieDBDao.getPopularMovies()
    }

    private suspend fun addPopularShows(results: Results) {
        val movie = Mapper().transformToPopularShowsEntity(results)
        theMovieDBDao.addPopularShow(movie)
    }

    fun getPopularShowsDB(): LiveData<List<PopularShowsEntity>> {
        return theMovieDBDao.getPopularShows()
    }

    private suspend fun addCurrentMovies(results: Results) {
        val movie = Mapper().transformToCurrentMoviesEntity(results)
        theMovieDBDao.addCurrentMovie(movie)
    }

    fun getCurrentMoviesDB(): LiveData<List<CurrentMoviesEntity>> {
        return theMovieDBDao.getCurrentMovies()
    }

    private suspend fun addCurrentShows(results: Results) {
        val movie = Mapper().transformToCurrentShowsEntity(results)
        theMovieDBDao.addCurrentShow(movie)
    }

    fun getCurrentShowsDB(): LiveData<List<CurrentShowsEntity>>{
        return theMovieDBDao.getCurrentShows()
    }
}