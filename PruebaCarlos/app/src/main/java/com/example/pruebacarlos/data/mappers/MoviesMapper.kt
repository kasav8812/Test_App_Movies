package com.example.pruebacarlos.data.mappers

import com.example.pruebacarlos.core.Mapper
import com.example.pruebacarlos.data.model.Movie
import com.example.pruebacarlos.data.model.MovieEntity
import com.example.pruebacarlos.data.model.ResultsItem
import com.example.pruebacarlos.data.model.toMovieEntity

class MoviesMapper(private val type : String) : Mapper<List<Movie>, List<MovieEntity>> {
    override suspend fun map(input: List<Movie>): List<MovieEntity> {
        return input.map { movie ->
            movie.toMovieEntity(type)
        }

    }
}