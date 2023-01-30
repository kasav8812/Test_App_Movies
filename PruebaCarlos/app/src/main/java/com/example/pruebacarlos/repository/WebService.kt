package com.example.pruebacarlos.repository

import com.example.pruebacarlos.data.model.MovieList
import com.example.pruebacarlos.data.model.VideoResponse
import com.example.pruebacarlos.repository.Api.NOW_PLAYING
import com.example.pruebacarlos.repository.Api.POPULARS
import com.example.pruebacarlos.repository.Api.TOP_RATED
import com.example.pruebacarlos.repository.Api.VIDEOS
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET(NOW_PLAYING)
    suspend fun getNowPlaying(@Query("api_key") apiKey: String): MovieList

    @GET(TOP_RATED)
    suspend fun getToRatedMovies(@Query("api_key") apiKey: String): MovieList

    @GET(POPULARS)
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList

    @GET(VIDEOS)
    suspend fun getVideosByIdMovie(@Path("idMovie") idMovie: String, @Query("api_key") apiKey: String, @Query("language") language: String): VideoResponse

}
