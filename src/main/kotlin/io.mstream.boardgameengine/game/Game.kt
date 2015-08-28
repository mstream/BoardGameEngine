package io.mstream.boardgameengine.game

import io.mstream.boardgameengine.board.*
import io.mstream.boardgameengine.move.*

abstract class Game(protected val eventSender: EventSender) {

    protected var gameState: GameState = GameState.NOT_STARTED

    abstract fun initialize()

    abstract fun makeMove(move: Move): MoveResult

    abstract fun possibleMoves(side: Side): Set<Move>
    abstract fun evaluation(side: Side): Int

    protected fun changePlayers() {
        gameState = when (gameState) {
            GameState.SIDE_A_IS_MOVING -> GameState.SIDE_B_IS_MOVING
            GameState.SIDE_B_IS_MOVING -> GameState.SIDE_A_IS_MOVING
            else                       -> throw IllegalStateException("game is not ongoing")
        }
    }

    protected fun movingSide(): Side {
        return when (gameState) {
            GameState.SIDE_A_IS_MOVING -> Side.A
            GameState.SIDE_B_IS_MOVING -> Side.B
            else                       -> throw IllegalStateException("game is not ongoing")
        }
    }
}