package com.example.kotlindemo.di

import com.example.kotlindemo.service.MoviesAPIs
import com.example.kotlindemo.service.MoviesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/* 
** Created by Rakesh on 2/16/2020.
*/

/**
 * This is module class injecting dependancy
 * Here we are providing api service object
 */

@Module
class MovieApiModule {
    private val BASE_URL = "https://api.themoviedb.org/"

    // it will provide movies api
    @Provides
    fun provideMovieAPI(): MoviesAPIs {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MoviesAPIs::class.java)
    }

    // lets get service for view models from here
    @Provides
    fun provideService(): MoviesService {
        return MoviesService()
    }
}