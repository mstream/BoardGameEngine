package io.mstream.boardgameengine.game

import com.google.common.eventbus.*
import io.mstream.boardgameengine.board.Board
import io.mstream.boardgameengine.move.Move
import io.mstream.boardgameengine.move.MoveResult

abstract class Game(protected val eventSender: EventSender) {

    abstract fun initialize()

    abstract fun makeMove(move: Move): MoveResult

}