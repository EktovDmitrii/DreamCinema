package com.example.dreamcinema.presentation.favouriteFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamcinema.databinding.FragmentFavouriteBinding
import com.example.dreamcinema.presentation.CourseRvModel
import com.example.dreamcinema.presentation.MovieApp
import com.example.dreamcinema.presentation.ViewModelFactory
import javax.inject.Inject


class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding: FragmentFavouriteBinding
        get() = _binding ?: throw RuntimeException("FragmentFavouriteBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as MovieApp).component
    }

    private lateinit var viewModel: FavouriteViewModel

    private lateinit var adapter: FavouriteAdapter

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
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavouriteViewModel::class.java]
        viewModel.getListFavouriteMovies()
        setAdapter()
        setObservers()
        binding.etSearchFavourite.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filteredLd.value = newText
                return true
            }
        })

    }

    private fun setObservers() {
        viewModel.mainMovieLD.observe(viewLifecycleOwner) {
            adapter.myData = it
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setAdapter() {
        courseRv = binding.rvFavouriteMovies
        courseList = ArrayList()
        val layoutManager = GridLayoutManager(context, 2)
        courseRv.layoutManager = layoutManager
        adapter = FavouriteAdapter(courseList, context)
        courseRv.adapter = adapter
        setupSwipeListener(binding.rvFavouriteMovies)
    }

    private fun setupSwipeListener(rvFavouriteMovie: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteFromFavourite(item)
                item.isInFavourite = false
                Log.d("isInFavouriteStatus", "${item.isInFavourite}")
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvFavouriteMovie)
    }

    companion object {

        fun newInstance() =
            FavouriteFragment()
    }
}