package com.example.appsuperheromvvm.data.model



import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuperHero(
    @SerializedName("response") val response: String = "",
    @SerializedName("results") val results: List<ResultsItemsResponse>
):Parcelable {

}

@Parcelize
data class ResultsItemsResponse(
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("powerstats") val powerstats: PowerstatsDetailResponse,
    @SerializedName("biography") val biography: BiographyDetailResponse,
    @SerializedName("image") val image: ImageResponse
):Parcelable
@Parcelize
data class ImageResponse(@SerializedName("url") val url: String = ""):Parcelable
@Parcelize
data class BiographyDetailResponse(
    @SerializedName("full-name") val fullName: String = "",
    @SerializedName("publisher") val publisher: String = ""
):Parcelable

@Parcelize
data class PowerstatsDetailResponse(
    @SerializedName("intelligence") val intelligence: String?,
    @SerializedName("strength") val strength: String?,
    @SerializedName("speed") val speed: String?,
    @SerializedName("durability") val durability: String?,
    @SerializedName("power") val power: String?,
    @SerializedName("combat") val combat: String?
):Parcelable





