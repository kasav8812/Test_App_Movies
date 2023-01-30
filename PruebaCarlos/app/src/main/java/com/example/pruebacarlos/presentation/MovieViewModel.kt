package com.example.pruebacarlos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.pruebacarlos.core.Resource
import com.example.pruebacarlos.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repo: MovieRepository) : ViewModel() {


    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(
                Resource.Success(
                    Triple(
                        repo.getNowPlayingMovies(),
                        repo.getPopularMovies(),
                        repo.getToRatedMovies()
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

}