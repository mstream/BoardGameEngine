package io.mstream.boardgameengine.game

import io.mstream.boardgameengine.board.*

enum class GameState {
    NOT_STARTED,
    SIDE_A_IS_MOVING,
    SIDE_B_IS_MOVING,
    SIDE_A_WON,
    SIDE_B_WON,
    DRAW;

    companion object {
        fun victoryOf(side: Side) = if (side == Side.A) SIDE_A_WON else SIDE_B_WON
    }

    fun isStarted() = this != NOT_STARTED
    fun isFinished() = this == SIDE_A_WON || this == SIDE_B_WON || this == DRAW
}
