package com.home.android_hotel.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.home.android_hotel.data.repository.HotelRepositoryImpl
import com.home.android_hotel.domain.GetHotelRoomsListUseCase
import com.home.android_hotel.domain.LoadHotelRoomsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomViewModel : ViewModel() {

    private val repository = HotelRepositoryImpl
    private val loadHotelRoomsUseCase = LoadHotelRoomsUseCase(repository)
    private val getHotelRoomsListUseCase = GetHotelRoomsListUseCase(repository)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadHotelRoomsUseCase.loadHotelRoomsList()
        }
    }

    val roomsList = getHotelRoomsListUseCase.getHotelRoomsList()
}