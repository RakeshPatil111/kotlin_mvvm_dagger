package com.example.kotlindemo.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


/*
** Created by Rakesh on 2/12/2020.
*/

/*
    This is file used for converting response from server, so that we can use for our view model
*/

data class Movies(
    val page: Integer?,

    @SerializedName("total_result")
    val totalResults: Integer?,

    @SerializedName("total_pages")
    val totalPages: Integer?,

    val results: List<Results>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("page"),
        TODO("totalResults"),
        TODO("totalPages"),
        parcel.createTypedArrayList(Results)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movies> {
        override fun createFromParcel(parcel: Parcel): Movies {
            return Movies(parcel)
        }

        override fun newArray(size: Int): Array<Movies?> {
            return arrayOfNulls(size)
        }
    }
}