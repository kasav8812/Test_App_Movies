package com.example.pruebacarlos.ui.movie.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.google.android.material.snackbar.Snackbar
import com.example.pruebacarlos.R
import com.example.pruebacarlos.core.Resource
import com.example.pruebacarlos.data.model.Movie
import com.example.pruebacarlos.databinding.FragmentMovieBinding
import com.example.pruebacarlos.presentation.MovieViewModel
import com.example.pruebacarlos.ui.gone
import com.example.pruebacarlos.ui.movie.adapters.GenericAdapter
import com.example.pruebacarlos.ui.movie.adapters.MoviesListAdapter
import com.example.pruebacarlos.ui.movie.adapters.OnOption
import com.example.pruebacarlos.ui.movie.adapters.TypeAdapter
import com.example.pruebacarlos.ui.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie), OnOption {
    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel>()
    private lateinit var concatAdapter: ConcatAdapter
    private var popularAdapter = MoviesListAdapter(this)
    private var topRaitedAdapter = MoviesListAdapter(this)
    private var upComingAdapter = MoviesListAdapter(this)
    private var popularList = listOf<Movie>()
    private var topRaitedList = listOf<Movie>()
    private var nowPlaying = listOf<Movie>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        observers()
    }

    private fun observers() {
        concatAdapter = ConcatAdapter()
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visible()
                }
                is Resource.Success -> {
                    binding.progressBar.gone()
                    with(result.data) {
                        popularList = first.results
                        topRaitedList = second.results
                        nowPlaying = third.results
                    }
                    submitList(popularList, topRaitedList, nowPlaying)

                    concatAdapter.apply {
                        addAdapter(
                            0, GenericAdapter(upComingAdapter, TypeAdapter.NOW_PLAYING)
                        )
                        addAdapter(
                            1, GenericAdapter(topRaitedAdapter, TypeAdapter.TOP_RATED)
                        )
                        addAdapter(
                            2, GenericAdapter(popularAdapter, TypeAdapter.POPULAR)
                        )
                    }
                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressBar.gone()
                    Snackbar.make(binding.root, "${result.exception}", Snackbar.LENGTH_SHORT)
                    Log.d("LiveData", "${result.exception}")
                }
            }
        }
    }

    private fun submitList(
        popularList: List<Movie>,
        topRaitedList: List<Movie>,
        upComingList: List<Movie>
    ) {
        popularAdapter.submitList(popularList)
        topRaitedAdapter.submitList(topRaitedList)
        upComingAdapter.submitList(upComingList)
    }

    override fun click(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.id,
            movie.poster_path,
            movie.backdrop_path ?: "",
            movie.vote_average,
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )

        findNavController().navigate(action)

    }

}

private fun List<Movie>.getDates(): List<String> {
    return this.map {
        it.release_date.substring(0, 4)
    }.distinct()
}

