package io.mstream.boardgameengine.game.tictactoe

import io.mstream.boardgameengine.board.*
import io.mstream.boardgameengine.game.*
import io.mstream.boardgameengine.move.Move
import io.mstream.boardgameengine.move.MoveResult
import io.mstream.boardgameengine.move.Select

class TicTacToe(eventSender: EventSender) : Game(eventSender) {

    private val crossPiece = Piece("cross", Side.A)
    private val circlePiece = Piece("circle", Side.B)
    private val board = Board(3)

    private var isFinished = false
    private var sideToMove = Side.A

    override fun initialize() {
        eventSender.post(BoardCreated(board.size))
    }

    override fun makeMove(move: Move): MoveResult {
        when {
            move !is Select                   -> return MoveResult.UNSUPPORTED
            !board.isInBounds(move.position)  -> return MoveResult.OUT_OF_BOUNDS
            !board.isFieldFree(move.position) -> return MoveResult.FIELD_OCCUPIED
            isFinished                        -> return MoveResult.GAME_IS_FINISHED
            else                              -> {
                board.putPieceAt(pieceOfMovingSide(), move.position)
                checkFinishConditions()
                sideToMove = sideToMove.opposite()
                return MoveResult.CORRECT
            }
        }
    }

    private fun pieceOfMovingSide() =
            if (sideToMove == Side.A) crossPiece else circlePiece

    private fun checkFinishConditions() {
        val lineChecker = LineChecker(board, 3)
        isFinished = when {
            board.isFull() -> true
            lineChecker.lines().isNotEmpty() -> true
            else -> false
        }
    }
}
