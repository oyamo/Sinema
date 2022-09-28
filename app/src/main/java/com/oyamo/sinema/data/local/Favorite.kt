package com.oyamo.sinema.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oyamo.sinema.util.Constants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Favorite(
    val favorite: Boolean,
    @PrimaryKey val mediaId: Int,
    val mediaType: String,
    val image: String,
    val title: String,
    val releaseDate: String,
    val rating: Float
)