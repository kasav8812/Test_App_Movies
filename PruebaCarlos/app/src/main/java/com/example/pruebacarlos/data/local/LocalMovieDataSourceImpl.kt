package com.example.pruebacarlos.data.local

import com.example.pruebacarlos.data.local.MovieDao
import com.example.pruebacarlos.data.model.MovieEntity
import com.example.pruebacarlos.data.model.MovieList
import com.example.pruebacarlos.data.model.toMovieList
import com.example.pruebacarlos.repository.TypeListMovie
import javax.inject.Inject

class LocalMovieDataSourceImpl @Inject constructor(private val movieDao: MovieDao) :
    LocalMovieDataSource {

    override suspend fun getNowPlayingMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == TypeListMovie.UP_COMMING.type }
            .toMovieList()
    }

    override suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == TypeListMovie.TOP_RATED.type }
            .toMovieList()
    }

    override suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == TypeListMovie.POPULAR.type }
            .toMovieList()
    }

    override suspend fun saveMovie(movie: MovieEntity) {
        movieDao.saveMovie(movie)
    }

    override suspend fun saveListMovie(listMovies: List<MovieEntity>) {
        listMovies.forEach { movie ->
            movieDao.saveMovie(movie)
        }
    }

}
