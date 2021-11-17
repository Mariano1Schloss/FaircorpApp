package com.faircorp.model.Dto

data class RoomDto(val id: Long,
                   val name: String,
                   val building: BuildingDto,
                   val current_temperature: Double?,
                   val target_temperature: Double?)