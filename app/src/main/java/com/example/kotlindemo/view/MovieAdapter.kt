package com.example.kotlindemo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.model.Results
import com.example.kotlindemo.util.getProgressDrawable
import com.example.kotlindemo.util.loadImage
import kotlinx.android.synthetic.main.animal_item.view.*

class MovieAdapter(private val moviesList: ArrayList<Results>) :
    RecyclerView.Adapter<MovieAdapter.AnimalViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        return AnimalViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.animal_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val result = moviesList[position]
        //Log.i("Poster path : ", result.posterPath)
        //holder.view.tvAnimalName.text = result.title
        holder.view.ivAnimal.loadImage(
            "https://image.tmdb.org/t/p/w500" + result.posterPath,
            getProgressDrawable(holder.view.context)
        )

        // set on click listener for movie
        holder.view.clMovie.setOnClickListener {
            val action = ListFragmentDirections.actionGoToDetails(result)
            Navigation.findNavController(holder.view).navigate(action)
        }
    }

    fun updateList(newAnimalList: List<Results>) {
        moviesList.clear()
        moviesList.addAll(newAnimalList)
        notifyDataSetChanged()
    }

    class AnimalViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}