package com.diegooliveira.rock_paper_scissors.domain.entity

data class RockPaperScissorsResponse(
    val cpu: String,
    val player: String?,
    val winner: String,
    val move: String?
)