package com.example.dummy.network.models

import com.google.gson.annotations.SerializedName

data class RandomDogPicModel(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)