package com.example.kotlindemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Rule
import java.util.concurrent.Executor

class ListFragmentViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun setupRxSxhedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, true)
            }
        }

        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler ->
            immediate
        }

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }
}