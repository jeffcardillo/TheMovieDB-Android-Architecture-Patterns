package com.jeffcardillo.androidsummit.themoviedb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jeffcardillo.androidsummit.themoviedb.api.TheMovieDbMovie
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeffcardillo.androidsummit.themoviedb.R
import com.jeffcardillo.androidsummit.themoviedb.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var adapter: MovieAdapter? = null
    lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        val root = binding.root

        recyclerView = root.findViewById(R.id.recyclerview)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(activity)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.sortController = viewModel.sortController

        viewModel.getPopularMovies().observe(this,
            Observer<List<TheMovieDbMovie>> { movieList ->
                activity?.let {
                    adapter = MovieAdapter(it, movieList)
                    recyclerView?.setAdapter(adapter)
                }
            })
    }
}
