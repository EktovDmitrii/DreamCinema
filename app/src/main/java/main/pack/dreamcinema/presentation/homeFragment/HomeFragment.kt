package main.pack.dreamcinema.presentation.homeFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import main.pack.dreamcinema.R
import main.pack.dreamcinema.databinding.FragmentHomeBinding
import main.pack.dreamcinema.domain.MovieInfo
import main.pack.dreamcinema.presentation.MovieApp
import main.pack.dreamcinema.presentation.ViewModelFactory
import main.pack.dreamcinema.presentation.detailFragment.MovieDetailFragment
import main.pack.dreamcinema.utils.subscribe
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

    private var adapterVertical: VerticalMovieInfoAdapter? = null

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
                }
            })
        binding.rvFilmInfoList.adapter = adapterVertical
        setObservers()
        viewModel.getAllMovieList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapterVertical = null
    }

    private fun setObservers() {
        subscribe(viewModel.listMovie) { it ->
            adapterVertical?.myData = it
            adapterVertical?.submitList(it)
        }
        subscribe(viewModel.isLoadingLifeData) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun launchDetailFragment(movieId: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, MovieDetailFragment.newInstance(movieId))
            .addToBackStack("detail")
            .commit()
    }

    companion object {

        fun newInstance(): Fragment {
            return HomeFragment()
        }
    }
}