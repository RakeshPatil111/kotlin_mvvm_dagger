package com.example.kotlindemo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.AnimalItemBinding
import com.example.kotlindemo.model.Results
import com.example.kotlindemo.util.OnMovieClick
import com.example.kotlindemo.view.fragment.ListFragmentDirections

/**
 * This adapter which shows each item
 * Data is set using data binding
 * the way of binding data to adapter as compared fragment or activity is different
 */

class MovieAdapter(private val moviesList: ArrayList<Results>) :
    RecyclerView.Adapter<MovieAdapter.AnimalViewHolder>(), OnMovieClick {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        return AnimalViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.animal_item,
                parent, false
            )
        )
    }

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val result = moviesList[position]
        holder.view.movie = result
        holder.view.onClick = this
    }

    fun updateList(newAnimalList: List<Results>) {
        moviesList.clear()
        moviesList.addAll(newAnimalList)
        notifyDataSetChanged()
    }

    override fun onClick(view: View) {
        for (movie: Results in moviesList) {
            if (view.tag == movie.title) {
                val action =
                    ListFragmentDirections.actionGoToDetails(movie)
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

    class AnimalViewHolder(var view: AnimalItemBinding) : RecyclerView.ViewHolder(view.root)
}