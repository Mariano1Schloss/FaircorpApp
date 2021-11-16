package com.faircorp.model.Dto

data class BuildingDto(val id: Long,
                   val name: String,val rooms: List<RoomDto>)