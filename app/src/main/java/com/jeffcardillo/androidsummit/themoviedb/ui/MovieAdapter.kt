package com.jeffcardillo.androidsummit.themoviedb.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.jeffcardillo.androidsummit.themoviedb.Config.THE_MOVIE_DB_POSTER_URL
import com.jeffcardillo.androidsummit.themoviedb.R
import com.jeffcardillo.androidsummit.themoviedb.api.TheMovieDbMovie

class MovieAdapter(private var context: Context, private var movieList: List<TheMovieDbMovie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]

        Glide.with(context)
            .load(THE_MOVIE_DB_POSTER_URL + movie.poster_path)
            .into(holder.imageView)

        holder.movieName.text = movie.title
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageView: ImageView = itemView.findViewById(R.id.poster)
        var movieName: TextView = itemView.findViewById(R.id.movie_name)

    }
}