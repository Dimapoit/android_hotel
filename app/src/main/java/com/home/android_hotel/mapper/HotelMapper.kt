package com.home.android_hotel.mapper

import com.home.android_hotel.RoomDto
import com.home.android_hotel.domain.RoomItem

class HotelMapper {

    fun mapDtoToEntity(roomDto: RoomDto): RoomItem {
        return RoomItem(
            id = roomDto.id ?: 0,
            name = roomDto.name ?: "",
            price = roomDto.price ?: 0,
            pricePer = roomDto.pricePer ?: "",
            peculiarities = roomDto.peculiarities.toList() ?: emptyList<String>(),
            imageUrls = roomDto.imageUrls.toList() )
    }
}