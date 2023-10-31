package com.diegooliveira.rock_paper_scissors

import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/list/all")
    suspend fun getDogBreeds(): ApiResponse
}