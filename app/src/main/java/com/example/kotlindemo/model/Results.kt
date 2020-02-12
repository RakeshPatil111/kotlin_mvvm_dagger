package com.example.kotlindemo.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


/* 
** Created by Rakesh on 2/12/2020.
*/


data class Results(
    val popularity: Double?,
    val voteCount: Integer?,
    val video: Boolean?,

    @SerializedName("poster_path")
    var posterPath: String?,

    val id: Integer?,
    val adult: Boolean?,
    @SerializedName("backdoor_path")
    val backdropPath: String?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    val title: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    val overview: String?,

    @SerializedName("release_date")
    val releaseDate: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Double::class.java.classLoader) as? Double,
        TODO("voteCount"),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        TODO("id"),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(popularity)
        parcel.writeValue(video)
        parcel.writeString(posterPath)
        parcel.writeValue(adult)
        parcel.writeString(backdropPath)
        parcel.writeString(originalLanguage)
        parcel.writeString(title)
        parcel.writeValue(voteAverage)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Results> {
        override fun createFromParcel(parcel: Parcel): Results {
            return Results(parcel)
        }

        override fun newArray(size: Int): Array<Results?> {
            return arrayOfNulls(size)
        }
    }
}