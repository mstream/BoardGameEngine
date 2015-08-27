package io.mstream.boardgameengine.game.tictactoe

import io.mstream.boardgameengine.*
import io.mstream.boardgameengine.board.*

class LineChecker(private val board: Board, private val winningLineLength: Int) {

    fun lines() =
            checkHorizontally()
                    .union(checkVertically())
                    .union(checkDiagonally())

    private fun checkHorizontally() = checkLine(0..0, 0..(board.size - 1), 1, 0)

    private fun checkVertically() = checkLine(0..(board.size - 1), 0..0, 0, 1)

    private fun checkDiagonally() =
            checkDiagonals()
                    .union(checkDiagonallyDescending())
                    .union(checkDiagonallyAscending())

    private fun checkDiagonallyDescending() =
            checkLine(
                    0..0,
                    0..(board.size - winningLineLength),
                    1, -1)
                    .union(checkLine(
                            0..(board.size - winningLineLength),
                            0..0,
                            1, -1))

    private fun checkDiagonallyAscending() =
            checkLine(0..0,
                    (winningLineLength - 1)..(board.size - 1),
                    1, 1)
                    .union(checkLine(
                            0..(board.size - winningLineLength),
                            (board.size - 1)..(board.size - 1),
                            1, 1))

    private fun checkDiagonals() =
            checkDescendingDiagonal()
                    .union(checkAscendingDiagonal())

    private fun checkDescendingDiagonal() = checkLine(0..0, 0..0, 1, 1)

    private fun checkAscendingDiagonal() =
            checkLine(
                    0..0,
                    (board.size - 1)..(board.size - 1),
                    1, -1)


    private fun checkLine(
            xStartRange: IntRange,
            yStartRange: IntRange,
            xDelta: Int,
            yDelta: Int): Set<Pair<Position, Position>> {
        var lines = emptySet<Pair<Position, Position>>()
        for (xStart in xStartRange) {
            for (yStart in yStartRange) {
                var currentSide: Side? = null
                var lineLength = 0
                var startPosition = Position.fromCords(xStart, yStart)
                var currentPosition = startPosition
                while (board.isInBounds(currentPosition)) {
                    val piece = board.pieceAt(currentPosition)
                    if (piece == null) {
                        lineLength = 0
                        currentSide = null
                    } else if (piece.side != currentSide) {
                        lineLength = 1
                        startPosition = currentPosition
                        currentSide = piece.side
                    } else if (++lineLength == winningLineLength) {
                        lines = lines.union(setOf(Pair(startPosition, currentPosition)))
                        break
                    }
                    if (currentPosition.x + xDelta < 0 || currentPosition.y + yDelta < 0) {
                        break
                    }
                    currentPosition = Position.fromCords(currentPosition.x + xDelta, currentPosition.y + yDelta)
                }
            }
        }
        return lines
    }
}


