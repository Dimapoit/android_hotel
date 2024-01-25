package com.home.android_hotel.domain

class GetHotelRoomsListUseCase(private val repository: HotelRepository) {
    fun getHotelRoomsList() = repository.getHotelRoomsList()
}