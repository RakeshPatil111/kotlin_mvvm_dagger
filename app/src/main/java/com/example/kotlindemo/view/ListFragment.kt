package com.example.kotlindemo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlindemo.R
import com.example.kotlindemo.model.Results
import com.example.kotlindemo.viewmodel.ListFragmentViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var listFragmentViewModel: ListFragmentViewModel
    private val listAnimalAdapter = MovieAdapter(arrayListOf())

    private val animalDataObserver = Observer<List<Results>> { list ->
        list?.let {
            rvAnimals.visibility = View.VISIBLE
            listAnimalAdapter.updateList(it)
        }

    }

    private val loadingObserver = Observer<Boolean> { isLoading ->
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        if (isLoading) {
            rvAnimals.visibility = View.GONE
            tvError.visibility = View.GONE
        }
    }

    private val errorObserver = Observer<Boolean> { isError ->
        tvError.visibility = if (isError) View.VISIBLE else View.GONE
        if (isError) {
            rvAnimals.visibility = View.GONE
            progressBar.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listFragmentViewModel = ViewModelProviders.of(this).get(ListFragmentViewModel::class.java)
        listFragmentViewModel.movies.observe(this, animalDataObserver)
        listFragmentViewModel.loading.observe(this, loadingObserver)
        listFragmentViewModel.loadError.observe(this, errorObserver)
        listFragmentViewModel.refresh()
        rvAnimals.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = listAnimalAdapter
        }

        refreshLayout.setOnRefreshListener {
            tvError.visibility = View.GONE
            rvAnimals.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            refreshLayout.isRefreshing = false
            listFragmentViewModel.refresh()
        }
    }
}
