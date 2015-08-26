package io.mstream.boardgameengine.game.tictactoe

import io.mstream.boardgameengine.*
import io.mstream.boardgameengine.game.*
import io.mstream.boardgameengine.move.*
import org.junit.*

class TicTacToeMovementTest {

    @Test fun shouldHandleUnsupportedMove() {
        val recordingEventSender = RecordingEventSender()
        val ticTacToe = TicTacToe(recordingEventSender)
        val moveResult = ticTacToe.makeMove(
                Drag(Position.fromCords(0, 0), Position.fromCords(1, 1)))
        Assert.assertEquals("should return UnsupportedMove when drag move is ordered",
                MoveResult.UNSUPPORTED_MOVE, moveResult)
    }

}