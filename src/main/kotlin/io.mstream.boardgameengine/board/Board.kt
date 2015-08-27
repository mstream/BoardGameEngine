package io.mstream.boardgameengine.board

import io.mstream.boardgameengine.Position

class Board(val size: Int) {

    private var pieces = emptyMap<Position, Piece>()

    fun pieceAt(position: Position): Piece? = pieces.get(position)

    fun putPieceAt(piece: Piece, position: Position) {
        pieces = pieces.plus(Pair(position, piece))
    }

    fun isFieldFree(position: Position) = pieceAt(position) == null

    fun isEmpty() = pieces.count() == 0

    fun isFull() = pieces.count() == size * size

    fun isInBounds(position: Position) =
            position.x >= 0 && position.y >= 0
                    && position.x < size && position.y < size
}

