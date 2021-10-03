package com.myapplication.utils

import com.myapplication.io.models.response.Results
import com.myapplication.io.room.entities.CurrentMoviesEntity
import com.myapplication.io.room.entities.CurrentShowsEntity
import com.myapplication.io.room.entities.PopularMoviesEntity
import com.myapplication.io.room.entities.PopularShowsEntity

class Mapper {
    fun transformToPopularMoviesEntity(result: Results): PopularMoviesEntity{
        return PopularMoviesEntity(
            result.id,
            result.adult,
            result.backdrop_path,
            result.original_language,
            result.original_title,
            result.overview,
            result.popularity,
            result.poster_path,
            result.release_date,
            result.title,
            result.video,
            result.vote_average,
            result.vote_count,
            result.first_air_date,
            result.name,
            result.original_name
        )
    }
    fun transformToPopularShowsEntity(result: Results): PopularShowsEntity{
        return PopularShowsEntity(
            result.id,
            result.adult,
            result.backdrop_path,
            result.original_language,
            result.original_title,
            result.overview,
            result.popularity,
            result.poster_path,
            result.release_date,
            result.title,
            result.video,
            result.vote_average,
            result.vote_count,
            result.first_air_date,
            result.name,
            result.original_name
        )
    }
    fun transformToCurrentMoviesEntity(result: Results): CurrentMoviesEntity{
        return CurrentMoviesEntity(
            result.id,
            result.adult,
            result.backdrop_path,
            result.original_language,
            result.original_title,
            result.overview,
            result.popularity,
            result.poster_path,
            result.release_date,
            result.title,
            result.video,
            result.vote_average,
            result.vote_count,
            result.first_air_date,
            result.name,
            result.original_name
        )
    }
    fun transformToCurrentShowsEntity(result: Results): CurrentShowsEntity{
        return CurrentShowsEntity(
            result.id,
            result.adult,
            result.backdrop_path,
            result.original_language,
            result.original_title,
            result.overview,
            result.popularity,
            result.poster_path,
            result.release_date,
            result.title,
            result.video,
            result.vote_average,
            result.vote_count,
            result.first_air_date,
            result.name,
            result.original_name
        )
    }
    fun transformToResults(result: PopularMoviesEntity): Results{
        return Results(
            id = result.id!!,
            adult = result.adult!!,
            backdrop_path = result.backdrop_path,
            original_language = result.original_language!!,
            original_title = result.original_title!!,
            overview = result.overview!!,
            popularity = result.popularity!!,
            poster_path = result.poster_path,
            release_date = result.release_date!!,
            title = result.title,
            video = result.video!!,
            vote_average = result.vote_average!!,
            vote_count = result.vote_count!!,
            first_air_date = result.first_air_date,
            name = result.name,
            original_name = result.original_name
        )
    }
    fun transformToResults(result: PopularShowsEntity): Results{
        return Results(
            id = result.id,
            adult = result.adult,
            backdrop_path = result.backdrop_path,
            original_language = result.original_language,
            original_title = result.original_title,
            overview = result.overview,
            popularity = result.popularity,
            poster_path = result.poster_path,
            release_date = result.release_date,
            title = result.title,
            video = result.video,
            vote_average = result.vote_average,
            vote_count = result.vote_count,
            first_air_date = result.first_air_date,
            name = result.name,
            original_name = result.original_name
        )
    }
    fun transformToResults(result: CurrentMoviesEntity): Results{
        return Results(
            id = result.id,
            adult = result.adult,
            backdrop_path = result.backdrop_path,
            original_language = result.original_language,
            original_title = result.original_title,
            overview = result.overview,
            popularity = result.popularity,
            poster_path = result.poster_path,
            release_date = result.release_date,
            title = result.title,
            video = result.video,
            vote_average = result.vote_average,
            vote_count = result.vote_count,
            first_air_date = result.first_air_date,
            name = result.name,
            original_name = result.original_name
        )
    }
    fun transformToResults(result: CurrentShowsEntity): Results{
        return Results(
            id = result.id,
            adult = result.adult,
            backdrop_path = result.backdrop_path,
            original_language = result.original_language,
            original_title = result.original_title,
            overview = result.overview,
            popularity = result.popularity,
            poster_path = result.poster_path,
            release_date = result.release_date,
            title = result.title,
            video = result.video,
            vote_average = result.vote_average,
            vote_count = result.vote_count,
            first_air_date = result.first_air_date,
            name = result.name,
            original_name = result.original_name
        )
    }
}