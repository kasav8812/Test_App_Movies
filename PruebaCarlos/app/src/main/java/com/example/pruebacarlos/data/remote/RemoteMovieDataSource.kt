package com.example.pruebacarlos.data.remote

import com.example.pruebacarlos.data.model.MovieList

interface RemoteMovieDataSource {

    suspend fun getNowPlayingMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies(): MovieList

}