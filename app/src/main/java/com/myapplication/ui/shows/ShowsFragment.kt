package com.myapplication.ui.shows

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

class ShowsFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var popularShowsList: List<Results>? = null
    private var currentShowsList: List<Results>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val myActivity = (activity as MainActivity)

        myActivity.showBottomNavigation(true)
        myActivity.titleText("Series")

        popularShowsList = myActivity.getPopularShows()
        currentShowsList = myActivity.getCurrentShows()

        val randomData = popularShowsList!!.random()
        val imageUrl = "https://image.tmdb.org/t/p/original" + randomData.poster_path
        Picasso.get().load(imageUrl).fit().into(binding.mainCover)

        initAdapters()

        binding.apply {
            currentMoviesRecycler.visibility = View.GONE
            currentMoviesText.visibility = View.GONE
            popularMoviesRecycler.visibility = View.GONE
            popularMoviesText.visibility = View.GONE
            mainCover.setOnClickListener {
                navigate(randomData)
            }
        }
        return binding.root
    }

    private fun initAdapters() {
        val adapterPopularShows = MoviesOrShowsAdapter(popularShowsList!!) { navigate(it) }
        val adapterCurrentShows = MoviesOrShowsAdapter(currentShowsList!!) { navigate(it) }
        binding.apply {
            popularShowsRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            currentShowsRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            popularShowsRecycler.adapter = adapterPopularShows
            currentShowsRecycler.adapter = adapterCurrentShows
        }
    }

    private fun navigate(result: Results){
        DetailsFragment.newArguments(result, Type.TV_SHOWS.value)
        findNavController().navigate(R.id.action_showsFragment_to_detailsFragment)
    }
}