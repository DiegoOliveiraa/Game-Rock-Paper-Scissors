package com.diegooliveira.rock_paper_scissors.data.mapper

import com.diegooliveira.rock_paper_scissors.data.source.remote.entity.RockPaperScissorsResponse
import com.diegooliveira.rock_paper_scissors.domain.entity.RockPaperScissorsResult

object RockPaperScissorsMapper {
    fun mapResponseToModel(response: RockPaperScissorsResponse): RockPaperScissorsResult {
        return RockPaperScissorsResult(
            response.cpu,
            response.player ?: "",
            response.winner,
            response.move ?: ""
        )
    }
}