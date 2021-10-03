package com.myapplication.di

import android.app.Application
import com.myapplication.R
import com.myapplication.io.retrofit.RetrofitBuilder
import com.myapplication.io.retrofit.TheMovieDBService
import com.myapplication.io.room.dao.MoviesShowsDao
import com.myapplication.io.room.database.TheMovieDBDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun getRetrofitService(): TheMovieDBService{
        return RetrofitBuilder.service
    }

    @Provides
    @Singleton
    fun getMoviesShowsDao(application: Application): MoviesShowsDao{
        return TheMovieDBDatabase.getDatabase(application).theMovieDBDao()
    }

    @Provides
    fun apiKeyProvider(application: Application): String{
        return application.applicationContext.getString(R.string.api_key)
    }
}