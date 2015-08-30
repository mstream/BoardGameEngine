package io.mstream.boardgameengine.game

import io.mstream.boardgameengine.board.*

enum class GameState {
    SIDE_A_IS_MOVING,
    SIDE_B_IS_MOVING,
    SIDE_A_WON,
    SIDE_B_WON,
    DRAW;

    companion object {

        fun victoryOf(side: Side) =
                if (side == Side.A) SIDE_A_WON else SIDE_B_WON

        fun turnOf(side: Side) =
                if (side == Side.A) SIDE_A_IS_MOVING else SIDE_B_IS_MOVING
    }

    fun isFinished() = this == SIDE_A_WON || this == SIDE_B_WON || this == DRAW

    fun isOngoing() = !isFinished()

}
