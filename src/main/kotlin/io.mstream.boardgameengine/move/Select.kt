package io.mstream.boardgameengine.move

import io.mstream.boardgameengine.*

data class Select private constructor(val position: Position) : Move {

    companion object {
        fun fromCords(x: Int, y: Int) = Select(Position.fromCords(x, y))
        fun fromPosition(position: Position) = Select(position)
    }
}