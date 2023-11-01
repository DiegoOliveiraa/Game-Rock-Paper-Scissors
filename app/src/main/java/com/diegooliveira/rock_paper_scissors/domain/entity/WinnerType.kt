package com.diegooliveira.rock_paper_scissors.domain.entity

enum class WinnerType(val tag: String) {
    VICTORY(tag = "player"),
    DEFEAT(tag = "cpu"),
    DRAW(tag = "draw");
    companion object {
        fun String.fromWinnerTag(): WinnerType? {
            return values().find { it.tag == this }
        }
    }
}