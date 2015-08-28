package io.mstream.boardgameengine.game.tictactoe

import io.mstream.boardgameengine.*
import io.mstream.boardgameengine.board.*
import io.mstream.boardgameengine.game.*
import io.mstream.boardgameengine.move.*

class TicTacToe(eventSender: EventSender) : Game(eventSender) {

    private val crossPiece = Piece("cross", Side.A)
    private val circlePiece = Piece("circle", Side.B)
    private val board = Board(3)

    override fun initialize() {
        gameState = GameState.SIDE_A_IS_MOVING
        eventSender.post(BoardCreated(board.size))
    }

    override fun makeMove(move: Move): MoveResult {
        when {
            move !is Select                   -> return MoveResult.UNSUPPORTED
            !board.isInBounds(move.position)  -> return MoveResult.OUT_OF_BOUNDS
            !board.isFieldFree(move.position) -> return MoveResult.FIELD_OCCUPIED
            !gameState.isStarted()            -> return MoveResult.GAME_NOT_STARTED
            gameState.isFinished()            -> return MoveResult.GAME_IS_FINISHED
            else                              -> {
                board.putPieceAt(pieceOfMovingSide(), move.position)
                updateState()
                return MoveResult.CORRECT
            }
        }
    }

    override fun possibleMoves(side: Side): Set<Move> {
        var moves = emptySet<Move>()
        for (x in 0..(board.size - 1)) {
            for (y in 0..(board.size - 1)) {
                val currentPosition = Position.fromCords(x, y)
                if (board.isFieldFree(currentPosition)) {
                    moves = moves
                            .union(setOf(Select.fromPosition(currentPosition)))
                }
            }
        }
        return moves
    }

    private fun pieceOfMovingSide() =
            if (movingSide() == Side.A) crossPiece else circlePiece

    private fun updateState() {
        val lines = LineChecker(board, 3).lines()
        when {
            lines.isNotEmpty() -> gameState = GameState.victoryOf(winningSide(lines))
            board.isFull()     -> gameState = GameState.DRAW
            else               -> changePlayers()
        }
    }

    private fun winningSide(lines: Set<Pair<Position, Position>>): Side {
        if (lines.isEmpty()) {
            throw IllegalArgumentException("lines can't be empty")
        }
        val startPosition = lines.first().first
        val pieceAtStartPosition = board.pieceAt(startPosition) ?:
                throw IllegalArgumentException("line doesn't contain all pieces")
        return pieceAtStartPosition.side
    }
}
