package com.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.myapplication.R
import com.myapplication.databinding.ActivityMainBinding
import com.myapplication.io.models.response.Results
import com.myapplication.io.models.response.TheMovieDBResponse
import com.myapplication.io.retrofit.TheMovieDBService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHost: NavHostFragment

    private var homeFlag = true
    private var moviesFlag = false
    private var tvShowsFlag = false

    companion object {

        private var popularMoviesList: List<Results>? = null
        private var popularShowsList: List<Results>? = null
        private var currentMoviesList: List<Results>? = null
        private var currentShowsList: List<Results>? = null

        fun newInstance(
            context: AppCompatActivity,
            popularMovies: List<Results>,
            popularShows: List<Results>,
            currentMovies: List<Results>,
            currentShows: List<Results>
        ) {
            popularMoviesList = popularMovies
            popularShowsList = popularShows
            currentMoviesList = currentMovies
            currentShowsList = currentShows
            val intent = Intent(context, MainActivity::class.java)
            startActivity(context, intent, null)
            context.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        navHost = NavHostFragment.create(R.navigation.nav_graph)
        hostNavigation()
        binding.apply {
            toolbarTitle.isSelected = true
            bottomNavigation.setOnItemSelectedListener {
                selectAction(it.itemId)
                true
            }
        }
        setContentView(binding.root)
    }

    private fun selectAction(id: Int) {
        when (id) {
            R.id.ic_home -> {
                when {
                    moviesFlag -> navHost.findNavController()
                        .navigate(R.id.action_movieFragment_to_homeFragment)
                    tvShowsFlag -> navHost.findNavController()
                        .navigate(R.id.action_showsFragment_to_homeFragment)
                }
                homeFlag = true
                moviesFlag = false
                tvShowsFlag = false
            }
            R.id.ic_movies -> {
                when {
                    homeFlag -> navHost.findNavController()
                        .navigate(R.id.action_homeFragment_to_movieFragment)
                    tvShowsFlag -> navHost.findNavController()
                        .navigate(R.id.action_showsFragment_to_movieFragment)
                }
                homeFlag = false
                moviesFlag = true
                tvShowsFlag = false
            }
            R.id.ic_tv_shows -> {
                when {
                    homeFlag -> navHost.findNavController()
                        .navigate(R.id.action_homeFragment_to_showsFragment)
                    moviesFlag -> navHost.findNavController()
                        .navigate(R.id.action_movieFragment_to_showsFragment)
                }
                homeFlag = false
                moviesFlag = false
                tvShowsFlag = true
            }
        }
    }

    fun getPopularMovies(): List<Results> = popularMoviesList!!
    fun getPopularShows(): List<Results> = popularShowsList!!
    fun getCurrentMovies(): List<Results> = currentMoviesList!!
    fun getCurrentShows(): List<Results> = currentShowsList!!
    fun showBottomNavigation(bool: Boolean){
        binding.bottomNavigation.visibility = if (bool) View.VISIBLE else View.GONE
    }
    fun titleText(text: String){
        binding.toolbarTitle.text = text
    }

    private fun hostNavigation() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, navHost)
            .setPrimaryNavigationFragment(navHost)
            .commit()
    }
}