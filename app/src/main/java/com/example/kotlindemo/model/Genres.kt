package com.example.kotlindemo.model


/* 
** Created by Rakesh on 2/12/2020.
*/
data class Genres(
    private val status: String?,
    private val tagline: String?,
    private val title: String?,
    private val releaseDate: String?,
    private val homepage: String?,
    private val imdbId: String?,
    private val originalLanguage: String?,
    private val originalTitle: String?,
    private val overview: String?,
    private val poster_path: String?,
    private val video: Boolean?,
    private val adult: Boolean?,
    private val voteAverage: Double?,
    private val voteCount: Integer?,
    private val id: Integer?,
    private val runtime: Integer?
)