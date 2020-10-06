package com.coderbot.movies.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coderbot.movies.R
import com.coderbot.movies.data.api.response.MovieItem
import com.coderbot.movies.presentation.view.activity.MoviesDetailsActivity
import kotlinx.android.synthetic.main.movie_item.view.*

import java.util.*

class MoviesRVAdapter() :
    RecyclerView.Adapter<MoviesRVAdapter.UsrViewHolder>() {

    private var data: List<MovieItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsrViewHolder {
        return UsrViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: UsrViewHolder, position: Int) {
        holder.bind(data[position])
    }


    fun swapData(data: List<MovieItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    class UsrViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MovieItem) = with(itemView) {
            val castStr = StringBuilder()
            item.cast.forEach { cast ->
                castStr.append(cast)
                if (cast != item.cast.last())
                    castStr.append(", ")

            }
            tvTitle.text = item.title
            tvCast.text = castStr.toString()
            ratingBar.rating = item.rating.toFloat()
            container.setOnClickListener {

                itemView.context.startActivity(
                    Intent(
                        itemView.context,
                        MoviesDetailsActivity::class.java
                    ).putExtra("movieItem", item)
                )
            }
        }

    }
}