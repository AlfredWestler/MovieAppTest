package com.myapplication.io.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myapplication.io.room.entities.*

@Dao
interface MoviesShowsDao {

    @Insert (entity = PopularMoviesEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPopularMovie(result: PopularMoviesEntity)

    @Query ("SELECT * FROM popular_movie_table")
    fun getPopularMovies(): LiveData<List<PopularMoviesEntity>>

    @Insert (entity = PopularShowsEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPopularShow(result: PopularShowsEntity)

    @Query ("SELECT * FROM popular_shows_table")
    fun getPopularShows(): LiveData<List<PopularShowsEntity>>

    @Insert (entity = CurrentMoviesEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCurrentMovie(result: CurrentMoviesEntity)

    @Query ("SELECT * FROM current_movies_table")
    fun getCurrentMovies(): LiveData<List<CurrentMoviesEntity>>

    @Insert (entity = CurrentShowsEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCurrentShow(result: CurrentShowsEntity)

    @Query ("SELECT * FROM current_shows_table")
    fun getCurrentShows(): LiveData<List<CurrentShowsEntity>>
}