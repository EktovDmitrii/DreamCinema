package com.example.dreamcinema.presentation.homeFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dreamcinema.R
import com.example.dreamcinema.databinding.FragmentHomeBinding
import com.example.dreamcinema.domain.MovieInfo
import com.example.dreamcinema.presentation.MovieApp
import com.example.dreamcinema.presentation.detailFragment.MovieDetailFragment
import com.example.dreamcinema.presentation.ViewModelFactory
import javax.inject.Inject


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as MovieApp).component
    }

    private lateinit var viewModel: HomeViewModel

    private lateinit var adapterVertical: VerticalMovieInfoAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        adapterVertical =
            VerticalMovieInfoAdapter(object : HorizontalMovieInfoAdapter.OnMovieClickListener {
                override fun onMovieClick(movieInfo: MovieInfo) {
                    launchDetailFragment(movieInfo.id)
                    Log.d("clickChecker", "click sucsessed ${movieInfo.id} ${movieInfo.title}")
                }
            })
        binding.rvFilmInfoList.adapter = adapterVertical
        setObservers()
        viewModel.getAllMovieList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObservers() {
        viewModel.listMovie.observe(viewLifecycleOwner) { listMovie ->
            adapterVertical.myData = listMovie
            adapterVertical.submitList(listMovie)
        }
    }

    private fun launchDetailFragment(movieId: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, MovieDetailFragment.newInstance(movieId))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        fun newInstance(): Fragment {
            return HomeFragment()
        }
    }
}