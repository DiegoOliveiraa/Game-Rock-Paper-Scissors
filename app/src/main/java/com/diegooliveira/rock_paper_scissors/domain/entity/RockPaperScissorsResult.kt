package com.diegooliveira.rock_paper_scissors.domain.entity

data class RockPaperScissorsResult(
    val cpu: String,
    val player: String,
    val winner: String,
    val move: String
)
