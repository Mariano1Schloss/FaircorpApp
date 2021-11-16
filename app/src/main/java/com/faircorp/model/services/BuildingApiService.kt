package com.faircorp.model.services
import com.faircorp.model.Dto.BuildingDto
import com.faircorp.model.Dto.WindowDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BuildingApiService {
    @GET("buildings")
    fun findAll(): Call<List<BuildingDto>>

    @GET("buildings/{id}")
    fun findById(@Path("id") id: Long): Call<BuildingDto>


}