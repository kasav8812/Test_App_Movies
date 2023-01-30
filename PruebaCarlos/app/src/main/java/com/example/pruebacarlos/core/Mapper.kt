package com.example.pruebacarlos.core

interface Mapper<I, O> {
    suspend fun map(input: I): O
}