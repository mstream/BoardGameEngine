package io.mstream.boardgameengine.move

import io.mstream.boardgameengine.Position

data class Select(val position: Position) : Move {

    companion object {
        fun fromCords(x: Int, y: Int) = Select(Position(x, y))
    }
}