package com.jeffcardillo.androidsummit.themoviedb.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jeffcardillo.androidsummit.themoviedb.api.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jeffcardillo.androidsummit.themoviedb.api.TheMovieDbMovie

class MainViewModel : ViewModel() {

    private val popularMovieService = ApiFactory.theMovieDbApi

    private var movieList: MutableLiveData<List<TheMovieDbMovie>>? = null

    var sortController = SortController()

    fun getPopularMovies() : LiveData<List<TheMovieDbMovie>> {
        if (movieList == null) {
            movieList = MutableLiveData()
            loadPopularMovies()
        }

        return movieList as LiveData<List<TheMovieDbMovie>>
    }

    fun sortMovies() {
        movieList?.let {
            if (sortController.sortAscending) {
                movieList?.setValue(movieList?.value?.sortedBy { it.title })
            } else {
                movieList?.setValue(movieList?.value?.sortedByDescending { it.title })
            }
        }

        sortController.switchNextSortDirection()
    }

    private fun loadPopularMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            val popularMovieRequest = popularMovieService.getPopularMovieAsync()
            try {
                val response = popularMovieRequest.await()
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    movieList?.setValue(movieResponse?.results)
                } else {
                    Log.d("MainViewModel ", response.errorBody().toString())
                }
            } catch (e: Exception) { }
        }
    }
}
