package com.example.dreamcinema.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dreamcinema.R
import com.example.dreamcinema.databinding.FragmentHomeBinding
import com.example.dreamcinema.databinding.FragmentMovieDetailBinding
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        private const val MOVIE_TITLE = "title"

        fun newInstance(title: String): Fragment {
            return MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(MOVIE_TITLE, title)
                }
            }
        }
    }
}