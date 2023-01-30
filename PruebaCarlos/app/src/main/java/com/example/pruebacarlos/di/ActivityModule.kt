package com.example.pruebacarloscom.example.pruebacarlos.di

import com.example.pruebacarlos.data.local.LocalMovieDataSource
import com.example.pruebacarlos.data.local.LocalMovieDataSourceImpl
import com.example.pruebacarlos.data.remote.RemoteMovieDataSource
import com.example.pruebacarlos.data.remote.RemoteMovieDataSourceImpl
import com.example.pruebacarlos.data.remote.RemoteVideosDataSource
import com.example.pruebacarlos.data.remote.RemoteVideosDataSourceImpl
import com.example.pruebacarlos.repository.MovieRepository
import com.example.pruebacarlos.repository.MovieRepositoryImpl
import com.example.pruebacarlos.repository.VideoRepository
import com.example.pruebacarlos.repository.VideoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    /*api movies */

    @Binds
    abstract fun bindMovieRepositoryImpl(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindRemoteMovieDataSourceImpl(remoteMovieDataSourceImpl: RemoteMovieDataSourceImpl): RemoteMovieDataSource

    @Binds
    abstract fun bindLocalMovieDataSourceImpl(localMovieDataSourceImpl: LocalMovieDataSourceImpl): LocalMovieDataSource


    /*api videos */

    @Binds
    abstract fun bindVideoRepositoryImpl(videoRepositoryImpl: VideoRepositoryImpl): VideoRepository

    @Binds
    abstract fun bindRemoteVideosDataSourceImpl(remoteVideosDataSourceImpl: RemoteVideosDataSourceImpl): RemoteVideosDataSource


}