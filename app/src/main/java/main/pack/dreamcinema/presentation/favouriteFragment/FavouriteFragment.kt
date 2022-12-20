package main.pack.dreamcinema.presentation.favouriteFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import main.pack.dreamcinema.R
import main.pack.dreamcinema.databinding.FragmentFavouriteBinding
import main.pack.dreamcinema.domain.MovieDetailInfo
import main.pack.dreamcinema.presentation.CourseRvModel
import main.pack.dreamcinema.presentation.MovieApp
import main.pack.dreamcinema.presentation.ViewModelFactory
import main.pack.dreamcinema.presentation.detailFragment.MovieDetailFragment
import main.pack.dreamcinema.utils.subscribe
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

    private var courseRv: RecyclerView? = null

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
        setSearchView()
    }

    private fun setSearchView() {
        binding.etSearchFavourite.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filteredLD.value = newText
                return true
            }
        })
    }

    override fun onStart() {
        super.onStart()
        setObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        courseRv = null
    }


    private fun setObservers() {
        subscribe(viewModel.mainMovieLD) {
            if (it != null) {
                adapter.myData = it
                adapter.submitList(it)
            }
        }
    }

    private fun setAdapter() {
        courseRv = binding.rvFavouriteMovies
        courseList = ArrayList()
        val layoutManager = GridLayoutManager(context, 2)
        courseRv?.layoutManager = layoutManager
        adapter =
            FavouriteAdapter(courseList, context, object : FavouriteAdapter.OnMovieClickListener {
                override fun onMovieClick(movieDetailInfo: MovieDetailInfo) {
                    launchDetailFragment(movieDetailInfo.id)
                }
            })
        courseRv?.adapter = adapter
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
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvFavouriteMovie)
    }

    private fun launchDetailFragment(movieId: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, MovieDetailFragment.newInstance(movieId))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = FavouriteFragment()
    }
}