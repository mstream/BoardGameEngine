package io.mstream.boardgameengine.game

import io.mstream.boardgameengine.board.Board
import io.mstream.boardgameengine.move.Move
import io.mstream.boardgameengine.move.MoveResult

interface Game {
    fun createBoard(): Board
    fun makeMove(move: Move): MoveResult
}