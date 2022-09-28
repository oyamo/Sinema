package com.oyamo.sinema.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.oyamo.sinema.model.Genre

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)