package com.jeffcardillo.androidsummit.themoviedb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jeffcardillo.androidsummit.themoviedb.api.TheMovieDbMovie
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeffcardillo.androidsummit.themoviedb.R
import com.jeffcardillo.androidsummit.themoviedb.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    var recyclerView: RecyclerView? = null
    var adapter: MovieAdapter? = null
    lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // FragmentDataBinding because of file name
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        val root = binding.root

        recyclerView = root.findViewById(R.id.recyclerview)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(activity)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = MainViewModel()

        // required to get button clicks
        binding.viewModel = viewModel

        viewModel.sortButtonText.observe(this, Observer<String> {
                buttonTitle -> sort_button.text = buttonTitle })

        viewModel.getPopularMovies().observe(this,
            Observer<List<TheMovieDbMovie>> { movieList ->
                activity?.let {
                    adapter = MovieAdapter(it, movieList)
                    recyclerView?.setAdapter(adapter)
                }
            })
    }
}
