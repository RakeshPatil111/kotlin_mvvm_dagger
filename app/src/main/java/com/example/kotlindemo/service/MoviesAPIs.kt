package com.example.kotlindemo.service

import com.example.kotlindemo.model.Genres
import com.example.kotlindemo.model.Movies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Query

interface MoviesAPIs {

    @GET("getPopularMovies.php")
    fun getAnimals(): Single<Movies>

    @GET("3/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Single<Movies>


    @GET("3/movie/popular")
    fun getPopularMoviesWithPage(@Query("api_key") apiKey: String, @Query("page") page: Int): Single<Movies>

    @GET("/movie/{movie_id}")
    fun getMovie(@Query("api_key") apiKey: String, @Part("movie_id") id: Int): Single<Genres>
}