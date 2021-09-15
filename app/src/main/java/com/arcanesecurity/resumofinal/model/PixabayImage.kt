package com.arcanesecurity.resumofinal.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PixabayImage(
    @SerializedName("total")
    val total: Int,
    @SerializedName("hits")
    val hits: List<Image>
)

@Entity
data class Image(
    @PrimaryKey
    val id: Int,
    val tags: String,
    val likes: Int,
    val previewURL: String?,
    val largeImageURL: String?,
    val fullHDURL: String?,
    val imageURL: String?,
    val webformatURL: String?,
    val user: String,
    val userImageURL: String,
) : Serializable
