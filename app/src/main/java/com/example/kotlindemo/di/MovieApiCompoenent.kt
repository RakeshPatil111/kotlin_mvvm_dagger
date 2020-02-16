package com.example.kotlindemo.di

import com.example.kotlindemo.service.MoviesService
import dagger.Component


/* 
** Created by Rakesh on 2/16/2020.
*/

@Component(modules = [MovieApiModule::class])
interface MovieApiCompoenent {

    // it tells, where we will be injecting i.e. in MoviesService
    fun injectService(service: MoviesService)
}