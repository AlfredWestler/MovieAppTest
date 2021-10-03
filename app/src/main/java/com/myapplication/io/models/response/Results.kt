package com.myapplication.io.models.response

import com.google.gson.annotations.SerializedName

data class Results(
        @SerializedName("adult") val adult : Boolean?,
        @SerializedName("backdrop_path") val backdrop_path : String?,
        @SerializedName("id") val id : Int?,
        @SerializedName("original_language") val original_language : String?,
        @SerializedName("original_title") val original_title : String?, //solo para movies
        @SerializedName("overview") val overview : String?,
        @SerializedName("popularity") val popularity : Double?,
        @SerializedName("poster_path") val poster_path : String?,
        @SerializedName("release_date") val release_date : String?,
        @SerializedName("title") val title : String?, //solo para movies
        @SerializedName("video") val video : Boolean?,
        @SerializedName("vote_average") val vote_average : Double?,
        @SerializedName("vote_count") val vote_count : Int?,
        @SerializedName("first_air_date") val first_air_date : String?, //solo para tv
        @SerializedName("name") val name : String?, //solo para tv
        @SerializedName("original_name") val original_name : String? //solo para tv
)