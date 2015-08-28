package io.mstream.boardgameengine.game.tictactoe

import io.mstream.boardgameengine.*
import io.mstream.boardgameengine.board.*
import io.mstream.boardgameengine.move.*
import org.junit.*

class TicTacToeEvaluationTest {

    @Test fun shouldHandleWinSituation() {
        val recordingEventSender = RecordingEventSender()
        val ticTacToe = TicTacToe(recordingEventSender)
        ticTacToe.initialize()
        ticTacToe.makeMove(Select.fromCords(0, 0))
        ticTacToe.makeMove(Select.fromCords(2, 0))
        ticTacToe.makeMove(Select.fromCords(0, 1))
        ticTacToe.makeMove(Select.fromCords(2, 1))
        ticTacToe.makeMove(Select.fromCords(0, 2))
        Assert.assertEquals("evaluation should be MAX for the side A",
                Int.MAX_VALUE, ticTacToe.evaluation(Side.A))
        Assert.assertEquals("evaluation should be MIN for the side B",
                Int.MIN_VALUE, ticTacToe.evaluation(Side.B))
    }
}