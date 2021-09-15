package com.arcanesecurity.resumofinal.adapter

import androidx.recyclerview.widget.DiffUtil
import com.arcanesecurity.resumofinal.model.Image

class ImageDiffUtilItemCallback : DiffUtil.ItemCallback<Image>() {

    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }

}