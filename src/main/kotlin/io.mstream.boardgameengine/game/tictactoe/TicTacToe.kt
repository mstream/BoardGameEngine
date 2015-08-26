package io.mstream.boardgameengine.game.tictactoe

import io.mstream.boardgameengine.board.Board
import io.mstream.boardgameengine.game.*
import io.mstream.boardgameengine.move.Move
import io.mstream.boardgameengine.move.MoveResult
import io.mstream.boardgameengine.move.Select

class TicTacToe(eventSender: EventSender) : Game(eventSender) {

    override fun initialize() {
        eventSender.post(BoardCreated(3))
    }

    override fun makeMove(move: Move): MoveResult {
        when {
            move !is Select -> return MoveResult.UNSUPPORTED_MOVE

            else -> return MoveResult.CORRECT
        }
    }
}