package com.example.dreamcinema.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dreamcinema.databinding.FragmentHomeBinding
import com.example.dreamcinema.presentation.adapter.HorizontalMovieInfoAdapter
import com.example.dreamcinema.presentation.adapter.VerticalMovieInfoAdapter
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

    private lateinit var viewModel: MovieInfoViewModel

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
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieInfoViewModel::class.java]
        adapterVertical = VerticalMovieInfoAdapter()
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

    companion object {

        fun newInstance(): Fragment {
            return HomeFragment()
        }

    }
}