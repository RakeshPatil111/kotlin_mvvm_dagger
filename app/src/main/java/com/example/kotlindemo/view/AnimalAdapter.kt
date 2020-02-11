package com.example.kotlindemo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.model.Animal
import com.example.kotlindemo.util.getProgressDrawable
import com.example.kotlindemo.util.loadImage
import kotlinx.android.synthetic.main.animal_item.view.*

class AnimalAdapter(private val animalList: ArrayList<Animal>) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        return AnimalViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.animal_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = animalList.size

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.view.tvAnimalName.text = animalList[position].name
        holder.view.ivAnimal.loadImage(
            animalList[position].image,
            getProgressDrawable(holder.view.context)
        )
    }

    fun updateList(newAnimalList: List<Animal>) {
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()
    }

    class AnimalViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}