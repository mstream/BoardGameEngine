package io.mstream.boardgameengine.game.tictactoe

import io.mstream.boardgameengine.board.Board
import io.mstream.boardgameengine.game.Game
import io.mstream.boardgameengine.move.Move
import io.mstream.boardgameengine.move.MoveResult

class TicTacToe : Game {

    override fun createBoard() = Board(3)

    override fun makeMove(move: Move): MoveResult {
        throw UnsupportedOperationException()
    }
}