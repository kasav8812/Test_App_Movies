package com.example.pruebacarlos.data.remote

import com.example.pruebacarlos.data.model.VideoResponse

interface RemoteVideosDataSource {

    suspend fun getVideosById(idMovie: String): VideoResponse

}