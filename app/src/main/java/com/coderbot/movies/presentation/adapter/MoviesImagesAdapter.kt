package com.coderbot.movies.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coderbot.movies.R
import kotlinx.android.synthetic.main.image_item.view.*
import java.util.*

class MoviesImagesAdapter() :
    RecyclerView.Adapter<MoviesImagesAdapter.ImageViewHolder>() {

    private var data: List<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.image_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(data[position])
    }


    fun swapData(data: List<String>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(imageUrl: String) = with(itemView) {
            ivMovie.loadImage(imageUrl)
        }
        private fun ImageView.loadImage(imageUrl: String) {
            Glide.with(this)
                .load(imageUrl)
                .into(this)
        }

    }
}