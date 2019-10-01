package com.jeffcardillo.androidsummit.themoviedb.api

import com.jeffcardillo.androidsummit.themoviedb.Config

object ApiFactory {
    val theMovieDbApi : TheMovieDbApi = RetrofitEndpointFactory.retrofitEndpoint(Config.THE_MOVIE_DB_BASE_URL).create(TheMovieDbApi::class.java)
}