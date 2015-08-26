package io.mstream.boardgameengine.game.tictactoe

import io.mstream.boardgameengine.board.Board
import io.mstream.boardgameengine.game.Game
import io.mstream.boardgameengine.move.Move
import io.mstream.boardgameengine.move.MoveResult
import io.mstream.boardgameengine.move.Select

class TicTacToe : Game {

    override fun createBoard() = Board(3)

    override fun makeMove(move: Move): MoveResult {
        when {
            move !is Select -> return MoveResult.UNSUPPORTED_MOVE

            else -> return MoveResult.CORRECT
        }
    }
}