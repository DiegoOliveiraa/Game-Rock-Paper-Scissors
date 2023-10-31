package com.diegooliveira.rock_paper_scissors.data.remote

import com.diegooliveira.rock_paper_scissors.domain.entity.MedievalNameResponse
import retrofit2.http.GET

interface ApiService {
    @GET("medieval_name")
    suspend fun getMedievalName(): MedievalNameResponse
}