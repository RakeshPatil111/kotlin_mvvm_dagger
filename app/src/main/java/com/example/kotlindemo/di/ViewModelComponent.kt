package com.example.kotlindemo.di

import com.example.kotlindemo.viewmodel.ListFragmentViewModel
import dagger.Component


/* 
** Created by Rakesh on 2/16/2020.
*/

@Component(modules = [MovieApiModule::class])
interface ViewModelComponent {
    fun inject(viewModel: ListFragmentViewModel)
}