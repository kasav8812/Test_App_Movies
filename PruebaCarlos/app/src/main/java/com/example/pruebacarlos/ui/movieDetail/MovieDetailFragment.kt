package com.example.pruebacarlos.ui.movieDetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pruebacarlos.R
import com.example.pruebacarlos.application.AppConstants.BASE_IMAGE
import com.example.pruebacarlos.core.Resource
import com.example.pruebacarlos.databinding.FragmentMovieDetailAnimBinding
import com.example.pruebacarlos.presentation.VideoViewModel
import com.example.pruebacarlos.ui.activities.VideoActivity
import com.example.pruebacarlos.ui.gone
import com.example.pruebacarlos.ui.loadImage
import com.example.pruebacarlos.ui.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail_anim), View.OnClickListener {

    private lateinit var binding: FragmentMovieDetailAnimBinding
    private val args by navArgs<MovieDetailFragmentArgs>()
    private val videosViewmodel by viewModels<VideoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailAnimBinding.bind(view)
        onclicks()
        setUpImages()
    }


    private fun setUpImages() {
        with(binding) {
            imgMovie.loadImage("${BASE_IMAGE}${args.posterImageUrl}")
            imgBackground.loadImage("${BASE_IMAGE}${args.backgroundIMageUrl}")
            toolbar.title = args.title
            contentLayout.txtOverview.text = args.overview
            contentLayout.tvLanguage.text = args.language
            contentLayout.tvYear.text = args.releaseDate.substring(0, 4)
            contentLayout.tvRaiting.text = args.voteAverage.toString()
        }
    }

    private fun onclicks() {
        binding.apply {
            imgMovie.setOnClickListener(this@MovieDetailFragment)
            btnSeeTrailer.setOnClickListener(this@MovieDetailFragment)
            imgBackground.setOnClickListener(this@MovieDetailFragment)
            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }


    override fun onClick(p0: View?) {
        videosViewmodel.getVideoById(args.id.toString()).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visible()
                }
                is Resource.Success -> {
                    binding.progressBar.gone()
                    startActivity(
                        Intent(
                            activity,
                            VideoActivity::class.java
                        ).apply {
                            putExtras(Bundle().apply {
                                putString(VideoActivity.KEY, result.data[0].url)
                                putString(VideoActivity.NAME, args.title) })
                        }
                    )
                }
                is Resource.Failure -> {
                    binding.progressBar.gone()
                }
            }
        }
    }
}
