package com.home.android_hotel.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.home.android_hotel.domain.HotelRepository
import com.home.android_hotel.domain.RoomItem
import com.home.android_hotel.data.network.ApiFactory
import com.home.android_hotel.mapper.HotelMapper

object HotelRepositoryImpl: HotelRepository {

    private val apiService = ApiFactory.apiService
    private val mapper = HotelMapper()

    private val roomsListLD = MutableLiveData<List<RoomItem>>()
    private var roomsList = mutableListOf<RoomItem>()

    fun setRoomsListLD() {
        roomsListLD.postValue(roomsList.toList())
    }

    override suspend fun loadHotelRooms() {

        val rooms = apiService.loadRooms().rooms
        val list = rooms?.map {

            mapper.mapDtoToEntity(it)
        }
        if (list != null) {
            roomsList.addAll(list)

            setRoomsListLD()
        }
    }

    override fun getHotelRoomsList(): LiveData<List<RoomItem>> {
        return roomsListLD
    }
}