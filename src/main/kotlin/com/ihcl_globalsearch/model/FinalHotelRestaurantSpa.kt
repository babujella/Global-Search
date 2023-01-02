package com.ihcl_globalsearch.model

import com.google.gson.annotations.SerializedName


data class FinalHotelRestaurantSpa(
    @SerializedName("Hotel")
    var hotel: List<Any>,
    @SerializedName("Restaurant")
    var restaurant:List<Any>,
    @SerializedName("Spa")
    var spa: List<Any>,
    @SerializedName("Destination")
    var destination: Set<Any>
)
