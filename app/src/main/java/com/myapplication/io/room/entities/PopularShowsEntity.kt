package com.myapplication.io.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_shows_table")
data class PopularShowsEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int?,
    val adult : Boolean?,
    val backdrop_path : String?,
    val original_language : String?,
    val original_title : String?,
    val overview : String?,
    val popularity : Double?,
    val poster_path : String?,
    val release_date : String?,
    val title : String?,
    val video : Boolean?,
    val vote_average : Double?,
    val vote_count : Int?,
    val first_air_date : String?,
    val name : String?,
    val original_name : String?
)