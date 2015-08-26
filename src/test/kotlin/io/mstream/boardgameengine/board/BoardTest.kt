package io.mstream.boardgameengine.board

import io.mstream.boardgameengine.Position
import org.junit.Assert
import org.junit.Test

class BoardTest {

    @Test fun canCheckBounds() {
        val board = Board(2)
        val isWithinBounds = board.isPositionInBounds(Position.fromCords(0, 0))
        val isOutOfBounds = !board.isPositionInBounds(Position.fromCords(3, 3))
        Assert.assertTrue("(0, 0) should be within bounds",
                isWithinBounds)
        Assert.assertTrue("(3, 3) should be out of bounds",
                isOutOfBounds)
    }

    @Test fun canCheckIfItIsEmpty() {
        val board = Board(2)
        val isEmptyWhenNotFilled = board.isEmpty()
        board.putPieceAt(Piece("piece", Side.A), Position.fromCords(0, 0))
        board.putPieceAt(Piece("piece", Side.B), Position.fromCords(1, 0))
        val isEmptyWhenPartiallyFilled = board.isEmpty()
        board.putPieceAt(Piece("piece", Side.A), Position.fromCords(0, 1))
        board.putPieceAt(Piece("piece", Side.B), Position.fromCords(1, 1))
        val isEmptyWhenCompletelyFilled = board.isEmpty()
        Assert.assertTrue("should be empty when not filled",
                isEmptyWhenNotFilled)
        Assert.assertFalse("shouldn't be empty when partially filled",
                isEmptyWhenPartiallyFilled)
        Assert.assertFalse("shouldn't be empty when completely filled",
                isEmptyWhenCompletelyFilled)
    }

    @Test fun canCheckIfItIsFull() {
        val board = Board(2)
        val isFullWhenEmpty = board.isFull()
        board.putPieceAt(Piece("piece", Side.A), Position.fromCords(0, 0))
        board.putPieceAt(Piece("piece", Side.B), Position.fromCords(1, 0))
        val isFullWhenPartiallyFilled = board.isFull()
        board.putPieceAt(Piece("piece", Side.A), Position.fromCords(0, 1))
        board.putPieceAt(Piece("piece", Side.B), Position.fromCords(1, 1))
        val isFullWhenCompletelyFilled = board.isFull()
        Assert.assertFalse("shouldn't be full when empty",
                isFullWhenEmpty)
        Assert.assertFalse("shouldn't be full when partially filled",
                isFullWhenPartiallyFilled)
        Assert.assertTrue("shouldn't be full when completely filled",
                isFullWhenCompletelyFilled)
    }

    @Test fun canCheckIfFieldIsFree() {
        val board = Board(2)
        board.putPieceAt(Piece("piece", Side.A), Position.fromCords(0, 0))
        board.putPieceAt(Piece("piece", Side.B), Position.fromCords(1, 0))
        val isFreeWhenOccupiedBySideA =
                board.isFieldFree(Position.fromCords(0, 0))
        val isFreeWhenOccupiedBySideB =
                board.isFieldFree(Position.fromCords(0, 0))
        val isFreeWhenNotOccupied =
                board.isFieldFree(Position.fromCords(0, 1)) &&
                        board.isFieldFree(Position.fromCords(1, 1))
        Assert.assertFalse("shouldn't be full when empty",
                isFreeWhenOccupiedBySideA)
        Assert.assertFalse("shouldn't be full when partially filled",
                isFreeWhenOccupiedBySideB)
        Assert.assertTrue("shouldn't be full when completely filled",
                isFreeWhenNotOccupied)
    }

    @Test fun canGetPieceFromField() {
        val board = Board(2)
        val piece = board.pieceAt(Position.fromCords(0, 0))
        Assert.assertNull("piece should be null",
                piece)
    }

    @Test fun canPutPiece() {
        val board = Board(2)
        val piece = Piece("piece", Side.A)
        val position = Position.fromCords(0, 0)
        board.putPieceAt(piece, position)
        Assert.assertEquals("piece should be inserted at the position",
                piece, board.pieceAt(position))
    }
}
