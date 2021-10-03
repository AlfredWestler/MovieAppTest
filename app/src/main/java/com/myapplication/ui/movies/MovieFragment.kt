package com.myapplication.ui.movies

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

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var popularMoviesList: List<Results>?=null
    private var currentMoviesList: List<Results>?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val myActivity = (activity as MainActivity)

        myActivity.showBottomNavigation(true)
        myActivity.titleText("Películas")

        popularMoviesList = myActivity.getPopularMovies()
        currentMoviesList = myActivity.getCurrentMovies()

        val randomData = popularMoviesList!!.random()
        val imageUrl = "https://image.tmdb.org/t/p/original"+ randomData.poster_path
        Picasso.get().load(imageUrl).fit().into(binding.mainCover)

        initAdapters()

        binding.apply {
            currentShowsRecycler.visibility = View.GONE
            popularShowsRecycler.visibility = View.GONE
            currentShowsText.visibility = View.GONE
            popularShowsText.visibility = View.GONE
            mainCover.setOnClickListener {
                navigate(randomData)
            }
        }
        return binding.root
    }

    private fun initAdapters() {
        val adapterPopularMovies = MoviesOrShowsAdapter(popularMoviesList!!){ navigate(it) }
        val adapterCurrentMovies = MoviesOrShowsAdapter(currentMoviesList!!){ navigate(it) }
        binding.apply {
            popularMoviesRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            currentMoviesRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            popularMoviesRecycler.adapter = adapterPopularMovies
            currentMoviesRecycler.adapter = adapterCurrentMovies
        }
    }

    private fun navigate(result: Results){
        DetailsFragment.newArguments(result, Type.MOVIES.value)
        findNavController().navigate(R.id.action_movieFragment_to_detailsFragment)
    }
}