package com.example.dreamcinema.presentation.genreFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamcinema.databinding.FragmentGenreBinding
import com.example.dreamcinema.presentation.MovieApp
import com.example.dreamcinema.presentation.ViewModelFactory
import com.example.dreamcinema.presentation.favouriteFragment.FavouriteFragment
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

    private lateinit var adapter: GenreAdapter

    private lateinit var courseRv: RecyclerView

    private lateinit var courseList: ArrayList<CourseRvModel>

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
        viewModel.getGenreInfo()
        setGridAdapter()
        setObservers()
    }

    private fun setGridAdapter() {
        courseRv = binding.rvCastInfo
        courseList = ArrayList()
        val layoutManager = GridLayoutManager(context, 2)
        courseRv.layoutManager = layoutManager
        adapter = GenreAdapter(courseList, context)
        courseRv.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObservers() {
        viewModel.genre.observe(viewLifecycleOwner) {
            adapter.myData = it
            adapter.submitList(it)
        }
    }

    companion object {

        fun newInstance() =
            FavouriteFragment()
    }
}