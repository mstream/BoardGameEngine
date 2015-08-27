package io.mstream.boardgameengine.game.tictactoe

import io.mstream.boardgameengine.*
import io.mstream.boardgameengine.game.*
import io.mstream.boardgameengine.move.*
import org.junit.*

class TicTacToeMovementTest {

    @Test fun shouldHandleValidMove() {
        val recordingEventSender = RecordingEventSender()
        val ticTacToe = TicTacToe(recordingEventSender)
        val moveResult = ticTacToe.makeMove(Select.fromCords(0, 0))
        Assert.assertTrue("should return CorrectMove when the game is ongoing and an empty field is selected",
                moveResult.isCorrect())
    }

    @Test fun shouldHandleUnsupportedMove() {
        val recordingEventSender = RecordingEventSender()
        val ticTacToe = TicTacToe(recordingEventSender)
        val moveResult = ticTacToe.makeMove(
                Drag(Position.fromCords(0, 0), Position.fromCords(1, 1)))
        Assert.assertEquals("should return UnsupportedMove when drag move is ordered",
                MoveResult.UNSUPPORTED, moveResult)
    }

    @Test fun shouldHandleOutOfBoundsMove() {
        val recordingEventSender = RecordingEventSender()
        val ticTacToe = TicTacToe(recordingEventSender)
        val moveResult = ticTacToe.makeMove(Select.fromCords(5, 5))
        Assert.assertEquals("should return OutOfBoundsMove when non existing field is selected",
                MoveResult.OUT_OF_BOUNDS, moveResult)
    }

    @Test fun shouldHandleMoveAtOccupiedField() {
        val recordingEventSender = RecordingEventSender()
        val ticTacToe = TicTacToe(recordingEventSender)
        ticTacToe.makeMove(Select.fromCords(0, 0))
        val moveResult = ticTacToe.makeMove(Select.fromCords(0, 0))
        Assert.assertEquals("should return FieldOccupiedMove when non empty field is selected",
                MoveResult.FIELD_OCCUPIED, moveResult)
    }

    @Test fun shouldHandleMoveAfterGameEnd() {
        val recordingEventSender = RecordingEventSender()
        val ticTacToe = TicTacToe(recordingEventSender)
        ticTacToe.makeMove(Select.fromCords(0, 0))
        ticTacToe.makeMove(Select.fromCords(1, 0))
        ticTacToe.makeMove(Select.fromCords(0, 1))
        ticTacToe.makeMove(Select.fromCords(1, 1))
        ticTacToe.makeMove(Select.fromCords(0, 2))
        val moveResult = ticTacToe.makeMove(Select.fromCords(1, 2))
        Assert.assertEquals("should return GameFinished when move was made after the game end",
                MoveResult.GAME_IS_FINISHED, moveResult)
    }

}