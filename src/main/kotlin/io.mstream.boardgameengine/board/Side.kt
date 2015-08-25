package io.mstream.boardgameengine.board

enum class Side {
    A,
    B;

    fun opposite(): Side = if (this == A) B else A
}

