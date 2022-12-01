package com.example.dreamcinema.presentation.genreFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.dreamcinema.databinding.FragmentGenreBinding
import com.example.dreamcinema.presentation.favouriteFragment.FavouriteFragment
import com.example.dreamcinema.presentation.MovieApp
import com.example.dreamcinema.presentation.ViewModelFactory
import javax.inject.Inject

class GenreFragment : Fragment() {

    private var _binding: FragmentGenreBinding? = null
    private val binding: FragmentGenreBinding
        get() = _binding ?: throw RuntimeException("FragmentGenreBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as MovieApp).component
    }

    private lateinit var viewModel: GenreViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[GenreViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {

        fun newInstance() =
            FavouriteFragment()
    }
}