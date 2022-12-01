package com.example.dreamcinema.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.dreamcinema.databinding.FragmentMovieDetailBinding
import com.example.dreamcinema.presentation.adapter.MovieCastAdapter
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

    private lateinit var viewModel: MovieDetailViewModel

    private lateinit var adapter: MovieCastAdapter

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
        viewModel.getDetailsInfo(movieId)
        viewModel.getCastInfo(movieId)
        adapter = MovieCastAdapter()
        binding.rvCastInfo.adapter = adapter

        setObservers()
    }

    private fun getMovieId(): Int {
        return requireArguments().getInt(MOVIE_ID, NO_MOVIE_ID)
    }

    private fun setObservers() {
        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            binding.tvMovieOverview.text = movie.overview
            binding.tvMovieDetailRate.text = movie.voteAverage.toString()
            binding.tvMovieDetailReleaseDate.text = movie.releaseDate
            binding.tvMovieDetailTitle.text = movie.title
            Glide.with(this).load(BASE_URL + movie.posterPath)
                .into(binding.ivMovieDetailPoster)
            Glide.with(this).load(BASE_POSTER_URL + movie.backdropPath)
                .into(binding.ivBackgroundPoster)
        }
        viewModel.cast.observe(viewLifecycleOwner){
            adapter.myData = it
        }
    }

    companion object {
        private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"
        private const val BASE_URL = "https://image.tmdb.org/t/p/original/"
        private const val MOVIE_ID = "movie_id"
        private const val NO_MOVIE_ID: Int = -1


        fun newInstance(movieId: Int): Fragment {
            return MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_ID, movieId)
                }
            }
        }
    }
}