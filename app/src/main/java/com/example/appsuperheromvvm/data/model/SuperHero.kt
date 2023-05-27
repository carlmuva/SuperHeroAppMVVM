package com.example.appsuperheromvvm.data.model

import com.google.gson.annotations.SerializedName

data class SuperHero(
    @SerializedName("response") val response: String = "",
    @SerializedName("results") val results: List<ResultsItemsResponse>
)

data class ResultsItemsResponse(
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("image") val image: ImageResponse
)

data class ImageResponse(@SerializedName("url") val url: String = "")





