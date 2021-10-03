package com.myapplication.io.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myapplication.io.room.dao.MoviesShowsDao
import com.myapplication.io.room.entities.*

@Database(
    entities = [PopularMoviesEntity::class, PopularShowsEntity::class, CurrentMoviesEntity::class, CurrentShowsEntity::class],
    version = 2,
    exportSchema = false
)
abstract class TheMovieDBDatabase : RoomDatabase(){

    abstract fun theMovieDBDao(): MoviesShowsDao

     companion object{
         @Volatile
         private var INSTANCE:TheMovieDBDatabase?=null

         fun getDatabase(context: Context): TheMovieDBDatabase{
             val tempInstance = INSTANCE
             if(tempInstance != null){
                 return  tempInstance
             }
             synchronized(this){
                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     TheMovieDBDatabase::class.java,
                     "the_movie_db_database"
                 ).build()
                 INSTANCE = instance
                 return instance
             }
         }
     }
}