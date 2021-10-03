package com.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.databinding.RecyclerItemBinding
import com.myapplication.io.models.response.Results
import com.squareup.picasso.Picasso

class MoviesOrShowsAdapter(private val list: List<Results>, private val item: (itemSelected: Results)->Unit): RecyclerView.Adapter<MoviesOrShowsAdapter.MoviesOrShowsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesOrShowsViewHolder {
        val view = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesOrShowsViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MoviesOrShowsViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener { item.invoke(list[position]) }
    }

    class MoviesOrShowsViewHolder(private val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(result: Results){
            val imageUrl = "https://image.tmdb.org/t/p/original"+ result.poster_path
            Picasso.get().load(imageUrl)
                .fit()
                .into(binding.posterImage)
        }
    }
}