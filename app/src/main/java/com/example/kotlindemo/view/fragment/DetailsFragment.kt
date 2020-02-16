package com.example.kotlindemo.view.fragment

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.FragmentDetailsBinding
import com.example.kotlindemo.model.AnimalPalette
import com.example.kotlindemo.model.Results
import kotlinx.android.synthetic.main.fragment_details.*

/**
 * This class used for showing details of Movie
 * Data is set using SafeArgument and Data Binding
 * Android Palette library is used to change background of activity according movie image
 */

class DetailsFragment : Fragment() {
    var movie: Results? = null
    private lateinit var dataBinding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get shared movie
        arguments?.let {
            movie = DetailsFragmentArgs.fromBundle(it).movie
        }

        dataBinding.movie = movie

        //set background to activity
        movie?.posterPath?.let {
            setBackground("https://image.tmdb.org/t/p/w500$it")
        }
    }

    private fun setBackground(url: String) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate {
                            val intColor = it?.darkMutedSwatch?.rgb ?: 0
                            clDetails.setBackgroundColor(intColor)
                            dataBinding.palette = AnimalPalette(intColor)

                            // this can be done using data bing ... consider this as TO DO and do it
                            tvMovieName.setTextColor(it?.lightMutedSwatch?.rgb ?: 0)
                            tvOverview.setTextColor(it?.lightMutedSwatch?.rgb ?: 0)
                        }
                }
            })
    }

}
