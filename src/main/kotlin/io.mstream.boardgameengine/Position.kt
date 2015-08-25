package io.mstream.boardgameengine

data class Position private constructor(val x: Int, val y: Int) {

    companion object {
        var positions = arrayOfNulls<Position>(100)

        fun fromCords(x: Int, y: Int): Position {
            if (x < 0 || y < 0) {
                throw IllegalArgumentException("cords can't be negative")
            }
            if (x > 9 || y > 9) {
                throw IllegalArgumentException("cords can't be bigger than 9")
            }
            val index = x * 10 + y
            val position = positions[index]
            when (position) {
                null -> {
                    val newPosition = Position(x, y)
                    positions[index] = newPosition
                    return newPosition
                }
                else -> return position
            }
        }
    }
}
