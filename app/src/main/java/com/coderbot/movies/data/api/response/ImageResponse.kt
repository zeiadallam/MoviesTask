package com.coderbot.movies.data.api.response

import com.google.gson.annotations.SerializedName


data class ImageResponse(
    @SerializedName("photo")
    val images: List<Image>
)

data class ImageWrapperResponse(
    @SerializedName("photos")
    val imageResponse: ImageResponse
)

data class Image(
    @SerializedName("owner")
    val owner: String = "",
    @SerializedName("server")
    val server: String = "",
    @SerializedName("ispublic")
    val ispublic: Int = 0,
    @SerializedName("isfriend")
    val isfriend: Int = 0,
    @SerializedName("farm")
    val farm: Int = 0,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("secret")
    val secret: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("isfamily")
    val isfamily: Int = 0
)
