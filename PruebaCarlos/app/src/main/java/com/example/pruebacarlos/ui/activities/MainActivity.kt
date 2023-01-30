package com.example.pruebacarlos.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebacarlos.R
import com.example.pruebacarlos.data.local.MovieDao
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var moviesDao: MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}