package com.diegooliveira.rock_paper_scissors.domain.entity

enum class RockPaperScissorsType(val tag: String) {
    ROCK(tag = "rock"),
    PAPER(tag = "paper"),
    SCISSORS(tag = "scissors");

    companion object {
        fun String.fromRockPaperScissorsType(): RockPaperScissorsType? {
            return values().find { it.tag == this }
        }
    }
}