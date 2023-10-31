package com.diegooliveira.rock_paper_scissors.data.source.remote.entity

data class RockPaperScissorsResult(
    val cpu: String,
    val player: String,
    val winner: String,
    val move: String
)
