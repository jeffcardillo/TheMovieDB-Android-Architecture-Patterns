package com.jeffcardillo.androidsummit.themoviedb.api

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface TheMovieDbApi {

    @GET("movie/popular")
    fun getPopularMovieAsync(): Deferred<Response<TheMovieDbResponse>>

}