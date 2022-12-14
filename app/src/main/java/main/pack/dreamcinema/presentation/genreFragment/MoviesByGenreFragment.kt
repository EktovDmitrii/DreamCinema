package main.pack.dreamcinema.presentation.genreFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import main.pack.dreamcinema.R
import main.pack.dreamcinema.databinding.FragmentMoviesByGenreBinding
import main.pack.dreamcinema.domain.MovieDetailInfo
import main.pack.dreamcinema.presentation.MovieApp
import main.pack.dreamcinema.presentation.ViewModelFactory
import main.pack.dreamcinema.presentation.detailFragment.MovieDetailFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesByGenreFragment : Fragment() {

    private var _binding: FragmentMoviesByGenreBinding? = null
    private val binding: FragmentMoviesByGenreBinding
        get() = _binding ?: throw RuntimeException("FragmentMoviesByGenreBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: GenreViewModel

    private var adapter = MovieByGenreAdapter(
        object : MovieByGenreAdapter.OnMovieClickListener {
            override fun onMovieClick(movieDetailInfo: MovieDetailInfo) {
                launchDetailFragment(movieDetailInfo.id)
            }
        }
    )

    private val component by lazy {
        (requireActivity().application as MovieApp).component
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesByGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[GenreViewModel::class.java]
        val genreID = getGenreId()
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getMoviesByGenre(genreID).collectLatest {
                adapter.submitData(lifecycle, it)
                binding.backImageView.setOnClickListener {
                    pressBack()
                }
            }
        }
        setAdapter()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setAdapter() {
        binding.rvMoviesByGenre.adapter = adapter
    }

    private fun getGenreId(): Int {
        return requireArguments().getInt(GENRE_ID, NO_GENRE_ID)
    }

    private fun pressBack() {
        requireActivity().onBackPressed()
    }

    private fun launchDetailFragment(movieId: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, MovieDetailFragment.newInstance(movieId))
            .addToBackStack(null)
            .commit()
    }


    companion object {

        private const val GENRE_ID = "genre_ids"
        private const val NO_GENRE_ID = -1


        fun newInstance(genreId: Int) =
            MoviesByGenreFragment().apply {
                arguments = Bundle().apply {
                    putInt(GENRE_ID, genreId)
                }
            }
    }
}