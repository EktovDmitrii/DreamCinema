package main.pack.dreamcinema.presentation.detailFragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import main.pack.dreamcinema.R
import main.pack.dreamcinema.databinding.FragmentMovieDetailBinding
import main.pack.dreamcinema.domain.MovieDetailInfo
import main.pack.dreamcinema.domain.MovieInfo
import main.pack.dreamcinema.presentation.MovieApp
import main.pack.dreamcinema.presentation.ViewModelFactory
import main.pack.dreamcinema.utils.*
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

    private lateinit var youTubeLoader: YouTubeLoader

    private lateinit var viewModel: MovieDetailViewModel

    private var castadapter: MovieCastAdapter? = null

    private var recommendationAdapter: MovieRecommendationAdapter? = null

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
        youTubeLoader = YouTubeLoader(lifecycle, binding.youtubePlayerView, binding.youtubeView)
        viewModel.getVideo(movieId)
        setObservers()
        viewModel.getDetailsInfo(movieId)
        viewModel.getCastInfo(movieId)
        viewModel.getRecommendedMovies(movieId)
        setAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        recommendationAdapter = null
        castadapter = null
    }

    private fun setAdapter() {
        castadapter = MovieCastAdapter()
        binding.rvCastInfo.adapter = castadapter
        recommendationAdapter = MovieRecommendationAdapter(
            object : MovieRecommendationAdapter.OnMovieClickListener {
                override fun onMovieClick(movieInfo: MovieInfo) {
                    launchDetailFragment(movieInfo.id)
                }
            }
        )
        binding.rvRecommendedMovies.adapter = recommendationAdapter

    }

    private fun getMovieId(): Int {
        return requireArguments().getInt(MOVIE_ID, NO_MOVIE_ID)
    }

    private fun setObservers() {
        subscribe(viewModel.video) {
            it
            youTubeLoader.loadVideo(it)
        }

        subscribe(viewModel.movie) {
            setAllBinds(it)
            stopPlayer()
            setFavouriteClickListener(it)
        }

        subscribe(viewModel.cast) {
            castadapter?.myData = it
            castadapter?.submitList(it)
        }

        subscribe(viewModel.recommendation) {
            recommendationAdapter?.myData = it
            recommendationAdapter?.submitList(it)
        }
    }

    private fun setAllBinds(movie: MovieDetailInfo) {
        with(movie) {
            binding.tvMovieOverview.text = overview
            binding.tvMovieDetailRate.text = voteAverage.toString()
            binding.tvMovieDetailReleaseDate.text = releaseDate
            binding.tvMovieDetailTitle.text = title
            if (posterPath != null) {
                Glide.with(this@MovieDetailFragment).load(BASE_URL + posterPath)
                    .into(binding.ivMovieDetailPoster)
            } else {
                Glide.with(this@MovieDetailFragment)
                    .load(R.drawable.ic_baseline_image_not_supported_24)
                    .into(binding.ivMovieDetailPoster)
            }
            if (backdropPath != null) {
                Glide.with(this@MovieDetailFragment).load(BASE_POSTER_URL + backdropPath)
                    .into(binding.ivBackgroundPoster)
            } else {
                Glide.with(this@MovieDetailFragment)
                    .load(R.drawable.ic_baseline_image_not_supported_24)
                    .into(binding.ivBackgroundPoster)
            }
            binding.backImageView.setOnClickListener {
                pressBack()
            }
        }
    }

    private fun pressBack() {
        requireActivity().onBackPressed()
    }

    private fun setFavouriteClickListener(movie: MovieDetailInfo) {
        binding.btnAddToFavourite.setOnClickListener {
            addToFavourite(movie)
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


    private fun stopPlayer() {
        binding.appbar.addOnCloseListener(
            onClose = {
                binding.youtubePlayerView.pause()
            },
            onOpen = {
                binding.youtubePlayerView.play()
            })
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

        fun newInstance(movieId: Int): Fragment {
            return MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_ID, movieId)
                }
            }
        }
    }
}