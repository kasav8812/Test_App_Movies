package com.example.pruebacarlos.repository

import com.example.pruebacarlos.core.InternetCheck
import com.example.pruebacarlos.data.local.LocalMovieDataSource
import com.example.pruebacarlos.data.mappers.MoviesMapper
import com.example.pruebacarlos.data.model.MovieList
import com.example.pruebacarlos.data.remote.RemoteMovieDataSource
import javax.inject.Inject

enum class TypeListMovie constructor(val type: String) {
    UP_COMMING("nowplaying"),
    TOP_RATED("toprated"),
    POPULAR("popular")
}

class MovieRepositoryImpl @Inject constructor(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    override suspend fun getNowPlayingMovies(): MovieList {
        return if (InternetCheck.isNetWorkAvailable()) {
            val listLocal =
                MoviesMapper(TypeListMovie.UP_COMMING.type).map(dataSourceRemote.getNowPlayingMovies().results)
            dataSourceLocal.saveListMovie(listLocal)
            dataSourceLocal.getNowPlayingMovies()
        } else {
            dataSourceLocal.getNowPlayingMovies()
        }
    }

    override suspend fun getToRatedMovies(): MovieList {
        return if (InternetCheck.isNetWorkAvailable()) {
            val listLocal =
                MoviesMapper(TypeListMovie.TOP_RATED.type).map(dataSourceRemote.getTopRatedMovies().results)
            dataSourceLocal.saveListMovie(listLocal)
            dataSourceLocal.getTopRatedMovies()
        } else {
            dataSourceLocal.getTopRatedMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {
        return if (InternetCheck.isNetWorkAvailable()) {
            val listLocal =
                MoviesMapper(TypeListMovie.POPULAR.type).map(dataSourceRemote.getPopularMovies().results)
            dataSourceLocal.saveListMovie(listLocal)
            dataSourceLocal.getPopularMovies()
        } else {
            dataSourceLocal.getPopularMovies()
        }
    }
}