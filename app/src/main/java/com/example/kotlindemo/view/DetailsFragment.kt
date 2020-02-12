package com.example.kotlindemo.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.kotlindemo.R
import com.example.kotlindemo.model.Results
import com.example.kotlindemo.util.getProgressDrawable
import com.example.kotlindemo.util.loadImage
import kotlinx.android.synthetic.main.fragment_details.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DetailsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    var movie: Results? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get shared movie
        arguments?.let {
            movie = DetailsFragmentArgs.fromBundle(it).movie
        }

        //set fields
        tvMovieName.text = movie?.title
        tvOverview.text = movie?.overview

        // get context to load image
        context?.let {
            ivMovie.loadImage(
                "https://image.tmdb.org/t/p/w500" + movie?.posterPath,
                getProgressDrawable(it)
            )
        }

        //set background to activity
        movie?.posterPath?.let {
            sutBackground("https://image.tmdb.org/t/p/w500$it")
        }
    }

    private fun sutBackground(url: String) {
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
                            tvMovieName.setTextColor(it?.lightVibrantSwatch?.rgb ?: 0)
                            tvOverview.setTextColor(it?.lightVibrantSwatch?.rgb ?: 0)
                        }
                }
            })
    }

}
