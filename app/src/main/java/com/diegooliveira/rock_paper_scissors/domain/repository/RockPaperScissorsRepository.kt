package com.diegooliveira.rock_paper_scissors.domain.repository

import com.diegooliveira.rock_paper_scissors.data.source.remote.entity.RockPaperScissorsResult

interface RockPaperScissorsRepository {
    suspend fun playGame(guess: String?): RockPaperScissorsResult
}