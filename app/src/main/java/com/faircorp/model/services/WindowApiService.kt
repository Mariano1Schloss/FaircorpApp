package com.faircorp.model.services

import com.faircorp.model.Dto.RoomDto
import com.faircorp.model.Dto.WindowDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WindowApiService {
    @GET("windows")
    fun findAll(): Call<List<WindowDto>>

    @GET("windows/{id}")
    fun findById(@Path("id") id: Long): Call<WindowDto>

    @GET("rooms/{id}/windows")
    fun findRoomWindows(@Path("id") id: Long): Call<List<WindowDto>>

    @PUT("windows/{id}/switch")
    fun ChangeWindowStatus (@Path("id") id: Long): Call<WindowDto>

    @PUT("rooms/{id}/switchWindow")
    fun ChangeRoomWindowsStatus(@Path("id")id : Long): Call<RoomDto>

}