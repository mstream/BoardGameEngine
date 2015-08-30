package io.mstream.boardgameengine.ai

import io.mstream.boardgameengine.*
import io.mstream.boardgameengine.board.*
import io.mstream.boardgameengine.game.*
import io.mstream.boardgameengine.move.*
import org.junit.*

class NegMaxDecisionEngineTest {

    @Test fun test() {
        val negMax = NegMaxDecisionEngine(4)
        val game = TestGame(Board(2), GameState.SIDE_A_IS_MOVING)
        var moves = emptyList<Move>()
        while (game.hasPossibleMoves()) {
            val move = negMax.chooseBestMove(game)
            moves += move
            game.makeMove(move)
        }
        val expectedMoves = listOf(
                Select.fromCords(1, 1),
                Select.fromCords(0, 1),
                Select.fromCords(1, 0),
                Select.fromCords(0, 0))
        Assert.assertEquals(expectedMoves, moves)
    }

    private class TestGame(
            board: Board,
            gameState: GameState) : Game(NullEventSender, board, gameState) {

        companion object {
            private val sideAPiece = Piece("sideAPiece", Side.A)
            private val sideBPiece = Piece("sideBPiece", Side.B)
        }

        override fun makeMove(move: Move): MoveResult {
            when (move) {
                is Select -> {
                    board.putPieceAt(pieceOfMovingSide(), move.position)
                    updateState()
                    return MoveResult.CORRECT
                }
            }
            return MoveResult.UNSUPPORTED
        }

        override fun possibleMoves(): Set<Move> {
            var moves = emptySet<Move>()
            if (gameState.isOngoing()) {
                for (x in 0..(board.size - 1)) {
                    for (y in 0..board.size - 1) {
                        val position = Position.fromCords(x, y)
                        if (board.isFieldFree(position)) {
                            moves = moves.union(setOf(Select.fromPosition(position)))
                        }
                    }
                }
            }

            return moves
        }

        override fun evaluation(): Int {
            return balance()
        }


        override fun clone() = TestGame(board.clone(), gameState)

        private fun pieceOfMovingSide() =
                if (movingSide() == Side.A) sideAPiece else sideBPiece

        private fun updateState() {
            if (!board.isFull()) {
                changeMovingSide()
                return
            }
            val balance = balance()
            gameState = when {
                balance == 0 -> GameState.DRAW
                balance > 0  -> GameState.SIDE_A_WON
                balance < 0  -> GameState.SIDE_B_WON
                else         -> throw IllegalStateException()
            }
        }

        private fun balance(): Int {
            var sideAScore = 0
            var sideBScore = 0
            for (x in 0..(board.size - 1)) {
                for (y in 0..board.size - 1) {
                    val position = Position.fromCords(x, y)
                    val fieldScore = 2 * y + x
                    val piece = board.pieceAt(position)
                    when (piece?.side) {
                        Side.A -> sideAScore += fieldScore
                        Side.B -> sideBScore += fieldScore
                    }
                }
            }
            return sideAScore - sideBScore
        }
    }
}

