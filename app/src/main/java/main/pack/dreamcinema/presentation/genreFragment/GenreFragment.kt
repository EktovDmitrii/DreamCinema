package main.pack.dreamcinema.presentation.genreFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import main.pack.dreamcinema.R
import main.pack.dreamcinema.databinding.FragmentGenreBinding
import main.pack.dreamcinema.domain.Genre
import main.pack.dreamcinema.presentation.CourseRvModel
import main.pack.dreamcinema.presentation.MovieApp
import main.pack.dreamcinema.presentation.ViewModelFactory
import main.pack.dreamcinema.utils.subscribe
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
        courseRv = binding.rvGenreList
        courseList = ArrayList()
        val layoutManager = GridLayoutManager(context, 2)
        courseRv.layoutManager = layoutManager
        adapter = GenreAdapter(
            courseList,
            context,
            object : GenreAdapter.OnGenreClickListener {
                override fun onGenreClick(genre: Genre) {
                    launchMovieFragment(genre.id)
                }
            }
        )
        courseRv.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchMovieFragment(genreId: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, MoviesByGenreFragment.newInstance(genreId))
            .addToBackStack(null)
            .commit()
    }

    private fun setObservers() {
        subscribe(viewModel.genre) {
            adapter.myData = it
            adapter.submitList(it)
        }
    }

    companion object {
        fun newInstance() =
            GenreFragment()
    }
}