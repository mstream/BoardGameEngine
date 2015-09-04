package io.mstream.boardgameengine.game

import io.mstream.boardgameengine.board.*
import io.mstream.boardgameengine.move.*

abstract class Game(
        protected val eventSender: EventSender,
        protected val board: Board,
        protected var gameState: GameState) {

    abstract fun makeMove(move: Move): MoveResult
    abstract fun possibleMoves(): Set<Move>
    abstract fun clone(): Game
    protected abstract fun ongoingGameEvaluation(): Int

    fun evaluation(): Int {
        return when (gameState) {
            GameState.victoryOf(Side.A) -> Int.MAX_VALUE
            GameState.victoryOf(Side.B) -> Int.MIN_VALUE
            GameState.DRAW              -> 0
            else                        -> ongoingGameEvaluation()
        }
    }


    fun hasPossibleMoves() = possibleMoves().count() > 0

    fun movingSide(): Side {
        return when (gameState) {
            GameState.SIDE_A_IS_MOVING -> Side.A
            GameState.SIDE_B_IS_MOVING -> Side.B
            else                       -> throw IllegalStateException("game is not ongoing")
        }
    }

    protected fun changeMovingSide() {
        gameState = when (gameState) {
            GameState.SIDE_A_IS_MOVING -> GameState.SIDE_B_IS_MOVING
            GameState.SIDE_B_IS_MOVING -> GameState.SIDE_A_IS_MOVING
            else                       -> throw IllegalStateException("game is not ongoing")
        }
    }
}