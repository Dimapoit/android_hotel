package com.home.android_hotel.data.network

import com.home.android_hotel.RoomsListDto
import retrofit2.http.GET

interface ApiService {

    @GET("8b532701-709e-4194-a41c-1a903af00195")
    suspend fun loadRooms(
    ): RoomsListDto
}