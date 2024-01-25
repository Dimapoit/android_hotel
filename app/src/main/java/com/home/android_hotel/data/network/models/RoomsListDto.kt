package com.home.android_hotel

import com.google.gson.annotations.SerializedName


data class RoomsListDto (

  @SerializedName("rooms" ) var rooms : ArrayList<RoomDto> = arrayListOf()

)