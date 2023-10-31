package com.diegooliveira.rock_paper_scissors.data.remote.api

import com.diegooliveira.rock_paper_scissors.data.remote.api.entity.MedievalNameResponse
import com.diegooliveira.rock_paper_scissors.data.remote.api.entity.RockPaperScissorsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("medieval_name")
    suspend fun getMedievalName(
        @Query("num") num: Int = 1,
        @Query("group") group: String = "all",
        @Query("gender") gender: String = "male",
        @Query("surname") includeSurname: Boolean = false
    ): MedievalNameResponse

    @GET("rock_paper_scissors/{guess}")
    suspend fun playGame(
        @Path("guess") guess: String?
    ): RockPaperScissorsResponse
}