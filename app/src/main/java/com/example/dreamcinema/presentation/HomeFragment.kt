package com.example.dreamcinema.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dreamcinema.databinding.FragmentHomeBinding
import com.example.dreamcinema.presentation.adapter.MovieInfoAdapter
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

    private lateinit var adapter: MovieInfoAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        adapter = MovieInfoAdapter()
        binding.rvFilmInfoList.adapter = adapter
        setObservers()
        viewModel.getTopMovieList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setObservers() {
        viewModel.listMovie.observe(viewLifecycleOwner) { movieInfo ->
            adapter.myData = movieInfo
            adapter.notifyDataSetChanged()
        }
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(): Fragment {
            return HomeFragment()
        }

    }
}