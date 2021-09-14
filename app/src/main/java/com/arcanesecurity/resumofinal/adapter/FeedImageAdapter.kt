package com.arcanesecurity.resumofinal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arcanesecurity.resumofinal.R
import com.arcanesecurity.resumofinal.databinding.FeedItemBinding
import com.arcanesecurity.resumofinal.model.Image
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class FeedImageAdapter : ListAdapter<Image, FeedImageViewHolder>(ImageDiffUtilItemCallback()) {

    var listOf = mutableListOf<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedImageViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false).apply {
            return FeedImageViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: FeedImageViewHolder, position: Int) {
        getItem(position).let { image -> holder.bind(image) }
    }

    fun update(newList: List<Image>, clear: Boolean = false) {
        if (clear) {
            listOf.clear()
        }
        listOf.addAll(newList)
        submitList(listOf.toMutableList())
    }

}

class FeedImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = FeedItemBinding.bind(itemView)

    fun bind(image: Image) {
        Glide.with(itemView)
            .load(image.largeImageURL)
//            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .listener(object : RequestListener<Drawable> {
//                override fun onLoadFailed(
//                    p0: GlideException?,
//                    p1: Any?,
//                    p2: Target<Drawable>?,
//                    p3: Boolean
//                ): Boolean {
//                    return false
//                }
//
//                override fun onResourceReady(
//                    p0: Drawable?,
//                    p1: Any?,
//                    p2: Target<Drawable>?,
//                    p3: DataSource?,
//                    p4: Boolean
//                ): Boolean {
//                    binding.bottomBarInfo.visibility = VISIBLE
//                    return false
//                }
//            })
            .into(binding.imageViewFeed)

    }

}