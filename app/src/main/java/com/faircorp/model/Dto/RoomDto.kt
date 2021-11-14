package com.faircorp.model.Dto

data class RoomDto(val id: Long,
                   val name: String,
                   val building: BuildingDto,
                   val currentTemperature: Double?,
                   val targetTemperature: Double?)