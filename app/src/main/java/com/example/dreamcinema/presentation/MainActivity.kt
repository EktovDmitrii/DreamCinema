package com.example.dreamcinema.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dreamcinema.R
import com.example.dreamcinema.databinding.ActivityMainBinding
import com.example.dreamcinema.presentation.detailFragment.MovieDetailFragment
import com.example.dreamcinema.presentation.favouriteFragment.FavouriteFragment
import com.example.dreamcinema.presentation.genreFragment.GenreFragment
import com.example.dreamcinema.presentation.homeFragment.HomeFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val movieDetailFragment = MovieDetailFragment()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as MovieApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        component.inject(this)
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0)
                finish()
        }
        val homeFragment = HomeFragment()
        val genreFragment = GenreFragment()
        val favouriteFragment = FavouriteFragment()
        launchRightFragment(homeFragment)
        setBottomNavMenu(homeFragment, favouriteFragment, genreFragment)
    }

    private fun setBottomNavMenu(
        homeFragment: HomeFragment,
        favouriteFragment: FavouriteFragment,
        genreFragment: GenreFragment
    ) {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    launchRightFragment(homeFragment)
                    false
                }
                R.id.favourite -> {
                    launchRightFragment(favouriteFragment)
                    false
                }
                R.id.genre -> {
                    launchRightFragment(genreFragment)
                    false
                }
                else ->
                    throw RuntimeException("")
            }
        }
        binding.bottomNavigationView.setBackgroundColor(
            resources.getColor(R.color.shadowDeepIndigo)
        )
    }

    private fun launchRightFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.home_fragment_container, fragment)
            addToBackStack(null)
                .commit()

        }
    }
}