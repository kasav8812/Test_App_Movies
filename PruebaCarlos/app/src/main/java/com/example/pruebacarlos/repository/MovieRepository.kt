package com.example.pruebacarlos.repository

import com.example.pruebacarlos.data.model.MovieList

interface MovieRepository {
    suspend fun getNowPlayingMovies() : MovieList
    suspend fun getToRatedMovies() : MovieList
    suspend fun getPopularMovies() : MovieList

}