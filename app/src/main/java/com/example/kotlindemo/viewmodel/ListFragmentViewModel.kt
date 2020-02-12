package com.example.kotlindemo.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kotlindemo.R
import com.example.kotlindemo.model.Movies
import com.example.kotlindemo.model.Results
import com.example.kotlindemo.service.MoviesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val movies by lazy { MutableLiveData<List<Results>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    private lateinit var myApplication: Application
    private val retrofit = MoviesService()
    private val disposable = CompositeDisposable()

    fun refresh() {
        loading.value = true
        myApplication = getApplication()
        getAnimals()
    }

    private fun getAnimals() {
        val key = myApplication.resources.getString(R.string.api_key)
        disposable.add(
            retrofit.getPopularMovies(key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Movies>() {
                    override fun onSuccess(t: Movies) {
                        loadError.value = false
                        loading.value = false
                        movies.value = t.results
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Error ::: ", e.localizedMessage)
                        loadError.value = true
                        loading.value = false
                        movies.value = null
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}