package com.example.pruebacarlos.repository

import com.example.pruebacarlos.data.mappers.VideosMapper
import com.example.pruebacarlos.data.model.VideosLocal
import com.example.pruebacarlos.data.remote.RemoteVideosDataSourceImpl
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val dataSourceRemoteImpl: RemoteVideosDataSourceImpl,
) : VideoRepository {
    override suspend fun getVideosById(idMovie: String): List<VideosLocal> {
        return VideosMapper().map(dataSourceRemoteImpl.getVideosById(idMovie).results)
    }
}