package com.example.dreamcinema.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dreamcinema.R
import com.example.dreamcinema.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as MovieApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        component.inject(this)


        val homeFragment = HomeFragment()
        val genreFragment = GenreFragment()
        val favouriteFragment = FavouriteFragment()
        launchHomeFragment(homeFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    launchHomeFragment(homeFragment)
                    true
                }
                R.id.favourite -> {
                    launchHomeFragment(favouriteFragment)
                    true
                }
                R.id.genre -> {
                    launchHomeFragment(genreFragment)
                    true
                }
                else ->
                    throw RuntimeException("")

            }
        }
        binding.bottomNavigationView.setBackgroundColor(
            resources.getColor(android.R.color.transparent)
        )
    }


    private fun launchHomeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.home_fragment_container, fragment)
                .addToBackStack(null)
                .commit()
            true
        }
    }
}