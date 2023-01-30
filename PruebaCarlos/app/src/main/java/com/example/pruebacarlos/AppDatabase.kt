package com.example.pruebacarlos

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pruebacarlos.data.local.MovieDao
import com.example.pruebacarlos.data.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}