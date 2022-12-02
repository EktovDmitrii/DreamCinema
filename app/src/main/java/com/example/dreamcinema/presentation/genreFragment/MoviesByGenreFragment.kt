package com.example.dreamcinema.presentation.genreFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.dreamcinema.databinding.FragmentMoviesByGenreBinding
import com.example.dreamcinema.presentation.MovieApp
import com.example.dreamcinema.presentation.ViewModelFactory
import javax.inject.Inject


class MoviesByGenreFragment : Fragment() {

    private var _binding: FragmentMoviesByGenreBinding? = null
    private val binding: FragmentMoviesByGenreBinding
    get() = _binding ?: throw RuntimeException("FragmentMoviesByGenreBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: GenreViewModel

    private lateinit var adapter: MovieByGenreAdapter


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
        viewModel.getMoviesByGenre(genreID)
        setObservers()
        setAdapter()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setAdapter(){
        adapter = MovieByGenreAdapter()
       binding.rvMoviesByGenre.adapter = adapter
    }

    private fun setObservers(){
        viewModel.movie.observe(viewLifecycleOwner){
            adapter.myData = it
            adapter.submitList(it)
        }
    }

    private fun getGenreId(): Int {
        return requireArguments().getInt(GENRE_ID, NO_GENRE_ID)
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