package com.diegooliveira.rock_paper_scissors.data.repository

import com.diegooliveira.rock_paper_scissors.data.mapper.RockPaperScissorsMapper
import com.diegooliveira.rock_paper_scissors.data.source.remote.api.ApiService
import com.diegooliveira.rock_paper_scissors.data.source.remote.entity.RockPaperScissorsResult
import com.diegooliveira.rock_paper_scissors.domain.repository.RockPaperScissorsRepository

class RockPaperScissorsRepositoryImpl(
    private val apiService: ApiService
) : RockPaperScissorsRepository {
    override suspend fun playGame(guess: String?): RockPaperScissorsResult {
        return RockPaperScissorsMapper.mapResponseToModel(apiService.playGame(guess))
    }
}