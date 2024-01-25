package com.home.android_hotel.domain

import androidx.lifecycle.LiveData

interface HotelRepository {

    suspend fun loadHotelRooms()
    fun getHotelRoomsList(): LiveData<List<RoomItem>>
}