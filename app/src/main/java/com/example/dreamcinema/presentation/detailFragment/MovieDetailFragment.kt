package com.example.dreamcinema.presentation.detailFragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.dreamcinema.R
import com.example.dreamcinema.databinding.FragmentMovieDetailBinding
import com.example.dreamcinema.domain.MovieDetailInfo
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.presentation.MovieApp
import com.example.dreamcinema.presentation.ViewModelFactory
import com.example.dreamcinema.utils.YouTubeLoader
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.fragment_movie_detail.view.*
import javax.inject.Inject

class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentMovieDetailBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as MovieApp).component
    }

    lateinit var youTubeLoader: YouTubeLoader

    private lateinit var viewModel: MovieDetailViewModel

    private lateinit var castadapter: MovieCastAdapter

    private lateinit var recommendationAdapter: MovieRecommendationAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = getMovieId()
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieDetailViewModel::class.java]
        youTubeLoader = YouTubeLoader(lifecycle, binding.youTubeView)
        setObservers()
        viewModel.getVideo(movieId)
        viewModel.getDetailsInfo(movieId)
        viewModel.getCastInfo(movieId)
        viewModel.getRecommendedMovies(movieId)
        setAdapter()
    }

    private fun setAdapter() {
        castadapter = MovieCastAdapter()
        binding.rvCastInfo.adapter = castadapter
        recommendationAdapter = MovieRecommendationAdapter(
            object : MovieRecommendationAdapter.OnMovieClickListener {
                override fun onMovieClick(movieInfo: MovieInfo) {
                    launchDetailFragment(movieInfo.id)
                    Log.d("clickChecker", "click sucsessed ${movieInfo.id} ${movieInfo.title}")
                }
            }
        )
        binding.rvRecommendedMovies.adapter = recommendationAdapter

    }

    private fun getMovieId(): Int {
        return requireArguments().getInt(MOVIE_ID, NO_MOVIE_ID)
    }

    private fun setObservers() {
        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            binding.tvMovieOverview.text = movie.overview
            setFavouriteClickListener(movie)
            binding.tvMovieDetailRate.text = movie.voteAverage.toString()
            binding.tvMovieDetailReleaseDate.text = movie.releaseDate
            binding.tvMovieDetailTitle.text = movie.title
            Glide.with(this).load(BASE_URL + movie.posterPath)
                .into(binding.ivMovieDetailPoster)
            Glide.with(this).load(BASE_POSTER_URL + movie.backdropPath)
                .into(binding.ivBackgroundPoster)
        }
        viewModel.cast.observe(viewLifecycleOwner) {
            castadapter.myData = it
            castadapter.submitList(it)
        }
        viewModel.recommendation.observe(viewLifecycleOwner) {
            recommendationAdapter.myData = it
            recommendationAdapter.submitList(it)
        }
        viewModel.video.observe(viewLifecycleOwner){
youTubeLoader.loadVideo("")
//            Log.d("VideoKey", "${it.key}")
        }
    }

    private fun setFavouriteClickListener(movie: MovieDetailInfo) {
        binding.btnAddToFavourite.setOnClickListener {
            Log.d("isInFavouriteStatus", "${movie.isInFavourite}")
            addToFavourite(movie)
            Log.d("isInFavouriteStatus", "${movie.isInFavourite}")
        }
        if (movie.isInFavourite) {
            setFavouriteButton()
        }
    }

    private fun addToFavourite(movieDetailInfo: MovieDetailInfo) {
        viewModel.addFavouriteMovie(movieDetailInfo)
        if (movieDetailInfo.isInFavourite) {
            Toast.makeText(
                requireContext(),
                ALREADY_IN_FAVOURITE,
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                requireContext(),
                ADD_TO_FAVOURITE,
                Toast.LENGTH_SHORT
            ).show()
            movieDetailInfo.isInFavourite = true
        }
        setFavouriteButton()
    }

    private fun setFavouriteButton() {
        with(binding.btnAddToFavourite) {
            text = getText(R.string.in_favourite)
            setBackgroundColor(Color.YELLOW)
            isEnabled = false
        }
    }

    private fun launchDetailFragment(movieId: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, newInstance(movieId))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"
        private const val BASE_URL = "https://image.tmdb.org/t/p/original/"
        private const val MOVIE_ID = "movie_id"
        private const val NO_MOVIE_ID: Int = -1
        private const val ADD_TO_FAVOURITE = "Film was added to favourite"
        private const val ALREADY_IN_FAVOURITE = "Already in your collection"
        private const val YOU_TUBE_API_KEY = "AIzaSyBnSI1FSYi-hEB8eANfuYe8_hRhAko3syM"


        fun newInstance(movieId: Int): Fragment {
            return MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_ID, movieId)
                }
            }
        }
    }
}