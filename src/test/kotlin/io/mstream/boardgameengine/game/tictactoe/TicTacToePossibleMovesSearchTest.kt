package io.mstream.boardgameengine.game.tictactoe

import io.mstream.boardgameengine.*
import io.mstream.boardgameengine.move.*
import org.junit.*


class TicTacToePossibleMovesSearchTest {

    @Test fun canFindAllPossibleMoves() {
        val recordingEventSender = RecordingEventSender()
        val ticTacToe = TicTacToe(recordingEventSender)
        ticTacToe.makeMove(Select.fromCords(0, 0))
        ticTacToe.makeMove(Select.fromCords(1, 0))
        ticTacToe.makeMove(Select.fromCords(2, 0))
        ticTacToe.makeMove(Select.fromCords(2, 1))
        ticTacToe.makeMove(Select.fromCords(2, 2))
        ticTacToe.makeMove(Select.fromCords(1, 2))
        ticTacToe.makeMove(Select.fromCords(0, 2))
        val possibleMoves = ticTacToe.possibleMoves()
        Assert.assertEquals("found moves should be (0, 1) and (1, 1)",
                setOf(Select.fromCords(0, 1), Select.fromCords(1, 1)),
                possibleMoves)
    }

}