package com.example.pruebacarlos.data.remote

import com.example.pruebacarlos.application.AppConstants
import com.example.pruebacarlos.repository.WebService
import javax.inject.Inject

class RemoteMovieDataSourceImpl @Inject constructor(private val webservice: WebService) :
    RemoteMovieDataSource {

    override suspend fun getNowPlayingMovies() =
        webservice.getNowPlaying(AppConstants.API_KEY)

    override suspend fun getTopRatedMovies() = webservice.getToRatedMovies(AppConstants.API_KEY)

    override suspend fun getPopularMovies() = webservice.getPopularMovies(AppConstants.API_KEY)

}