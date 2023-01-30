package com.example.pruebacarlos.data.remote

import com.example.pruebacarlos.application.AppConstants
import com.example.pruebacarlos.repository.WebService
import javax.inject.Inject

enum class Languages constructor(val type: String) {
    ESPANOL("es-ES"),
    ENGLISH("en_EN"),
}

class RemoteVideosDataSourceImpl @Inject constructor(private val webservice: WebService) :
    RemoteVideosDataSource {

    override suspend fun getVideosById(idMovie: String) =
        webservice.getVideosByIdMovie(idMovie, AppConstants.API_KEY, Languages.ESPANOL.type)

}