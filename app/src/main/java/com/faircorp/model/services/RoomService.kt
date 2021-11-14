package com.faircorp.model.services

import com.faircorp.model.Dto.RoomDto

class RoomService {
    companion object {
        // Fake rooms
        val ROOMS: List<RoomDto> = listOf(
            RoomDto(1, "Room EF 6.10",BuildingService.BUILDINGS[0], 18.2, 20.0),
            RoomDto(2, "Hall", BuildingService.BUILDINGS[2],18.2,18.0),
            RoomDto(3, "Room EF 7.10", BuildingService.BUILDINGS[1],21.2, 20.0),
            RoomDto(4, "Room EF 7.10", BuildingService.BUILDINGS[1],21.2, 20.0)
        )
    }
    fun findById(id: Long) = ROOMS.firstOrNull { it.id == id}

    fun findAll() = ROOMS.sortedBy { it.name }
}