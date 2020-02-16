package com.example.kotlindemo.service

import com.example.kotlindemo.di.DaggerMovieApiCompoenent
import javax.inject.Inject

class MoviesService {

    @Inject
    lateinit var retrofit: MoviesAPIs

    // here we are getting our object from dagger
    init {
        DaggerMovieApiCompoenent.create().injectService(this)
    }

    fun getPopularMovies(apiKey: String) = retrofit.getPopularMovies(apiKey)
}