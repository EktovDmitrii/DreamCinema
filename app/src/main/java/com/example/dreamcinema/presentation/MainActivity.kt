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
        launchHomeFragment()



    }


    private fun launchHomeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, HomeFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}