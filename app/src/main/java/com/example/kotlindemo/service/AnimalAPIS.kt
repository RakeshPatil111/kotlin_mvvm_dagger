package com.example.kotlindemo.service

import com.example.kotlindemo.model.Animal
import io.reactivex.Single
import retrofit2.http.GET

interface AnimalAPIS {

    @GET("getAnimals.php")
    fun getAnimals(): Single<List<Animal>>
}