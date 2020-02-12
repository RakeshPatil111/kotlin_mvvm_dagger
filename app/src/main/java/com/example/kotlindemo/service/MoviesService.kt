package com.example.kotlindemo.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MoviesService {
    private val BASE_URL = "https://api.themoviedb.org/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MoviesAPIs::class.java)

    fun getPopularMovies(apiKey: String) = retrofit.getPopularMovies(apiKey)
}