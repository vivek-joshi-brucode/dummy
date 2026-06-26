package com.example.dummy.network.models

import com.google.gson.annotations.SerializedName

data class Object(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("data")
    val data: Data?

)

data class Data(
    @SerializedName("Generation")
    val generation: String?,
    @SerializedName("Price")
    val price: String?,
    @SerializedName("Capacity")
    val capacity: String?,
    @SerializedName("Screen size")
    val screenSize: Double?,
    @SerializedName("Color")
    val color: String?,
    @SerializedName("Description")
    val description: String?,
    @SerializedName("Strap Colour")
    val strapColour: String?,
    @SerializedName("Case Size")
    val caseSize: String?,
    val year: Long?,
    @SerializedName("price")
    val price2: Double?,
    @SerializedName("CPU model")
    val cpuModel: String?,
    @SerializedName("Hard disk size")
    val hardDiskSize: String?,
    @SerializedName("generation")
    val generation2: String?,
    @SerializedName("color")
    val color2: String?,
    @SerializedName("capacity GB")
    val capacityGb: Long?,
    @SerializedName("capacity")
    val capacity2: String?,
)
