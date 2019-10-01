package com.jeffcardillo.androidsummit.themoviedb.api

data class TheMovieDbMovie (
    val id: Int,
    val vote_average: Double,
    val title: String,
    val overview: String,
    val adult: Boolean,
    val poster_path: String
)

data class TheMovieDbResponse (
    val results: List<TheMovieDbMovie>
)