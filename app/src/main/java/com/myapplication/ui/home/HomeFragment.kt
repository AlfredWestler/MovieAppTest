package com.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.R
import com.myapplication.databinding.FragmentHomeBinding
import com.myapplication.io.models.enums.Type
import com.myapplication.io.models.response.Results
import com.myapplication.ui.MainActivity
import com.myapplication.ui.adapter.MoviesOrShowsAdapter
import com.myapplication.ui.detail.DetailsFragment
import com.squareup.picasso.Picasso
import kotlin.random.Random

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var popularMoviesList: List<Results>?=null
    private var popularShowsList: List<Results>?=null
    private var currentMoviesList: List<Results>?=null
    private var currentShowsList: List<Results>?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val myActivity = (activity as MainActivity)

        myActivity.showBottomNavigation(true)
        myActivity.titleText("Series / Películas")
        popularMoviesList = myActivity.getPopularMovies()
        popularShowsList = myActivity.getPopularShows()
        currentMoviesList = myActivity.getCurrentMovies()
        currentShowsList = myActivity.getCurrentShows()

        binding.apply {
            val random = Random.nextInt(0,1)
            val randomData = if(random == 0) popularMoviesList!!.random() else popularShowsList!!.random()
            val imageUrl = "https://image.tmdb.org/t/p/original"+ randomData.poster_path
            Picasso.get().load(imageUrl).fit().into(mainCover)
            mainCover.setOnClickListener {
                val type = if(random == 0) Type.MOVIES.value else Type.TV_SHOWS.value
                navigate(randomData,type)
            }
        }
        initAdapters()
        return binding.root
    }

    private fun initAdapters() {
        val adapterPopularMovies = MoviesOrShowsAdapter(popularMoviesList!!){ navigate(it, Type.MOVIES.value) }
        val adapterPopularShows = MoviesOrShowsAdapter(popularShowsList!!){ navigate(it, Type.TV_SHOWS.value) }
        val adapterCurrentMovies = MoviesOrShowsAdapter(currentMoviesList!!){ navigate(it, Type.MOVIES.value) }
        val adapterCurrentShows = MoviesOrShowsAdapter(currentShowsList!!){ navigate(it, Type.TV_SHOWS.value) }
        binding.apply {
            popularMoviesRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            popularShowsRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            currentMoviesRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            currentShowsRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            popularMoviesRecycler.adapter = adapterPopularMovies
            popularShowsRecycler.adapter = adapterPopularShows
            currentMoviesRecycler.adapter = adapterCurrentMovies
            currentShowsRecycler.adapter = adapterCurrentShows
        }
    }

    private fun navigate(result: Results, type: String){
        DetailsFragment.newArguments(result, type)
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
    }
}

fun main(){
    val inte = Random.nextInt(0,1)
    println(inte)
}