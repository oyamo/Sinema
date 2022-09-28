package com.oyamo.sinema.data.remote.responses


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.oyamo.sinema.model.Cast
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreditsResponse(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("id")
    val id: Int
): Parcelable