package com.example.pruebacarlos.repository

import com.example.pruebacarlos.data.model.VideosLocal

interface VideoRepository {
    suspend fun getVideosById(idMovie: String): List<VideosLocal>
}