package com.faircorp.model.services

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServices {
    val windowsApiService : WindowApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://app-ddb00d24-3cab-4d55-9cb6-d9d53eaa8f98.cleverapps.io/api/")
            .build()
            .create(WindowApiService::class.java)
        }

    val buildingsApiService : BuildingApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://app-ddb00d24-3cab-4d55-9cb6-d9d53eaa8f98.cleverapps.io/api/")
            .build()
            .create(BuildingApiService::class.java)
    }

    val roomApiService : RoomApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://app-ddb00d24-3cab-4d55-9cb6-d9d53eaa8f98.cleverapps.io/api/")
            .build()
            .create(RoomApiService::class.java)
    }
}

