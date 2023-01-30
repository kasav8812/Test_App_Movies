package com.example.pruebacarlos.data.local

import com.example.pruebacarlos.data.model.MovieEntity
import com.example.pruebacarlos.data.model.MovieList

interface LocalMovieDataSource {
    suspend fun getNowPlayingMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
    suspend fun saveMovie(movie: MovieEntity)
    suspend fun saveListMovie(listMovies: List<MovieEntity>)

}