package io.mstream.boardgameengine.game.tictactoe

import org.junit.Assert
import org.junit.Test

class TicTacToeTest {

    @Test fun shouldCreateSizeOfThreeBoard() {
        val ticTacToe = TicTacToe()
        val board = ticTacToe.createBoard()
        Assert.assertEquals(3, board.size)
    }

    @Test fun shouldCreateEmptyBoard() {
        val ticTacToe = TicTacToe()
        val board = ticTacToe.createBoard()
        Assert.assertTrue(board.isEmpty())
    }
}