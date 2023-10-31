package com.diegooliveira.rock_paper_scissors.data.remote.api.entity

data class RockPaperScissorsResponse(
    val cpu: String,
    val player: String?,
    val winner: String,
    val move: String?
)