package com.home.android_hotel.domain

class LoadHotelRoomsUseCase(private val repository: HotelRepository) {
    suspend fun loadHotelRoomsList() = repository.loadHotelRooms()
}