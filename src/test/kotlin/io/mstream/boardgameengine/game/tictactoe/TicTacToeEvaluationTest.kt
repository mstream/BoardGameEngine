package io.mstream.boardgameengine.game.tictactoe

import io.mstream.boardgameengine.*
import io.mstream.boardgameengine.move.*
import org.junit.*

class TicTacToeEvaluationTest {

    @Test fun shouldHandleWinSituation() {
        val recordingEventSender = RecordingEventSender()
        val ticTacToe = TicTacToe(recordingEventSender)
        ticTacToe.makeMove(Select.fromCords(0, 0))
        ticTacToe.makeMove(Select.fromCords(2, 0))
        ticTacToe.makeMove(Select.fromCords(0, 1))
        ticTacToe.makeMove(Select.fromCords(2, 1))
        ticTacToe.makeMove(Select.fromCords(0, 2))
        Assert.assertEquals("evaluation should be MAX for the side A",
                Int.MAX_VALUE, ticTacToe.evaluation())
    }

    @Test fun shouldRateCenterHigherThanCorner() {
        val recordingEventSender = RecordingEventSender()
        val ticTacToe = TicTacToe(recordingEventSender)
        ticTacToe.makeMove(Select.fromCords(1, 1))
        ticTacToe.makeMove(Select.fromCords(0, 0))
        Assert.assertTrue("evaluation should be higher for the side A",
                ticTacToe.evaluation() > 0)
    }

    @Test fun shouldRateCornerHigherThanEdge() {
        val recordingEventSender = RecordingEventSender()
        val ticTacToe = TicTacToe(recordingEventSender)
        ticTacToe.makeMove(Select.fromCords(0, 0))
        ticTacToe.makeMove(Select.fromCords(1, 0))
        Assert.assertTrue("evaluation should be higher for the side A",
                ticTacToe.evaluation() > 0)
    }
}