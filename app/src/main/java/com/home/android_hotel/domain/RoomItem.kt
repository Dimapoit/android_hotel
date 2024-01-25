package com.home.android_hotel.domain

data class RoomItem(
    var id: Int,
    var name: String,
    var price: Int,
    var pricePer: String,
    var peculiarities: List<String> = arrayListOf(),
    var imageUrls: List<String> = arrayListOf()
)
