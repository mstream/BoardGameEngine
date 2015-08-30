package io.mstream.boardgameengine.game.tictactoe

import io.mstream.boardgameengine.*
import io.mstream.boardgameengine.board.*
import io.mstream.boardgameengine.game.*
import io.mstream.boardgameengine.move.*

class TicTacToe(
        eventSender: EventSender = NullEventSender,
        board: Board = Board(3),
        gameState: GameState = GameState.SIDE_A_IS_MOVING) :
        Game(eventSender, board, gameState) {

    companion object {
        private val crossPiece = Piece("cross", Side.A)
        private val circlePiece = Piece("circle", Side.B)
    }

    init {
        eventSender.post(BoardCreated(board.size))
    }

    override fun makeMove(move: Move): MoveResult {
        when {
            move !is Select                   -> return MoveResult.UNSUPPORTED
            !board.isInBounds(move.position)  -> return MoveResult.OUT_OF_BOUNDS
            !board.isFieldFree(move.position) -> return MoveResult.FIELD_OCCUPIED
            gameState.isFinished()            -> return MoveResult.GAME_IS_FINISHED
            else                              -> {
                board.putPieceAt(pieceOfMovingSide(), move.position)
                updateState()
                return MoveResult.CORRECT
            }
        }
    }

    override fun possibleMoves(): Set<Move> {
        var moves = emptySet<Move>()
        if (gameState.isOngoing()) {
            for (x in 0..(board.size - 1)) {
                for (y in 0..(board.size - 1)) {
                    val currentPosition = Position.fromCords(x, y)
                    if (board.isFieldFree(currentPosition)) {
                        moves = moves
                                .union(setOf(Select.fromPosition(currentPosition)))
                    }
                }
            }
        }
        return moves
    }

    override fun evaluation(): Int {
        return when (gameState) {
            GameState.victoryOf(Side.A) -> Int.MAX_VALUE
            GameState.victoryOf(Side.B) -> Int.MIN_VALUE
            else                        -> 0
        }
    }

    override fun clone() = TicTacToe(NullEventSender, board.clone(), gameState)

    private fun pieceOfMovingSide() =
            if (movingSide() == Side.A) crossPiece else circlePiece

    private fun updateState() {
        val lines = LineChecker(board, 3).lines()
        when {
            lines.isNotEmpty() -> gameState = GameState.victoryOf(winningSide(lines))
            board.isFull()     -> gameState = GameState.DRAW
            else               -> changeMovingSide()
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
