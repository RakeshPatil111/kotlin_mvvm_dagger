package com.example.kotlindemo.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kotlindemo.model.Animal
import com.example.kotlindemo.service.AnimalService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val animals by lazy { MutableLiveData<List<Animal>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val retrofit = AnimalService()
    private val disposable = CompositeDisposable()

    fun refresh() {
        loading.value = true
        getAnimals()
    }

    private fun getAnimals() {
        disposable.add(
            retrofit.getAnimals()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Animal>>() {
                    override fun onSuccess(t: List<Animal>) {
                        loadError.value = false
                        loading.value = false
                        animals.value = t
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Error ::: ", e.localizedMessage)
                        loadError.value = true
                        loading.value = false
                        animals.value = null
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}