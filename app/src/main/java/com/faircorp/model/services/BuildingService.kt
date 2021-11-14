package com.faircorp.model.services

import com.faircorp.model.Dto.BuildingDto

class BuildingService {
    companion object {
        val BUILDINGS : List<BuildingDto> = listOf(
            BuildingDto(1, "Main Building"),
            BuildingDto(2, "Administration Building"),
            BuildingDto(3, "Student Residence")
        )

    }

    fun findById(id: Long) = BUILDINGS.firstOrNull { it.id == id}

    fun findAll() = BUILDINGS.sortedBy { it.name }
}