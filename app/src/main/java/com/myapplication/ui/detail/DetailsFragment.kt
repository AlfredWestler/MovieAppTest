package com.myapplication.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.myapplication.R
import com.myapplication.databinding.FragmentDetailsBinding
import com.myapplication.io.models.response.Results
import com.myapplication.io.repository.DetailRepository
import com.myapplication.ui.MainActivity
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailViewModel
    private var idVideo = ""
    @Inject lateinit var repository: DetailRepository

    companion object{
        private var data: Results ?= null
        private var type: String = ""
        fun newArguments(result: Results, type: String){
            data = result
            this.type = type
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val myActivity = (activity as MainActivity)
        myActivity.showBottomNavigation(false)
        myActivity.titleText("")

        viewModel.apply {
            getVideo(repository, type, data?.id!!)
            errorMessage.observe(viewLifecycleOwner, {
                binding.playVideoImage.visibility = View.GONE
            })
            videos.observe(viewLifecycleOwner, {
                if(it.results.isNullOrEmpty()){
                    binding.playVideoImage.visibility = View.GONE
                }else{
                    idVideo = it.results[0].key
                    binding.playVideoImage.visibility = View.VISIBLE
                }
            })
        }


        binding.apply {
            val imageUrl = "https://image.tmdb.org/t/p/original"+ data?.poster_path
            Picasso.get().load(imageUrl).fit().into(movieImage)
            titleText.text = data?.title ?: data?.name
            tvDate.text = data?.release_date ?: data?.first_air_date
            tvLanguaje.text = data?.original_language
            tvOverview.text = data?.overview
            tvGenre.text = data?.original_title ?: data?.original_name
            tvAverage.text = data?.vote_average.toString()
            tvPopularity.text = "(${data?.popularity} Reviews)"
            playVideoImage.setOnClickListener { openYoutubeStandAlonePlayer(idVideo, autoplay = true, lightMode = true) }
        }

        return binding.root
    }

    private fun openYoutubeStandAlonePlayer(videoID: String, autoplay: Boolean = false, lightMode: Boolean = false) {
        val intent = YouTubeStandalonePlayer.createVideoIntent(
            requireActivity(),
            requireContext().getString(R.string.developer_key),
            videoID,
            0, //startIndex
            autoplay,
            lightMode
        )
        startActivity(intent)
    }
}