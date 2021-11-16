package com.faircorp.model.services

import com.faircorp.model.Dto.BuildingDto
import com.faircorp.model.Dto.RoomDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RoomApiService {
    @GET("rooms")
    fun findAll(): Call<List<RoomDto>>

    @GET("rooms/{id}")
    fun findById(@Path("id") id: Long): Call<RoomDto>

    @GET("buildings/{id}/rooms")
    fun findBuildingRooms(@Path("id") id: Long): Call<List<RoomDto>>
}