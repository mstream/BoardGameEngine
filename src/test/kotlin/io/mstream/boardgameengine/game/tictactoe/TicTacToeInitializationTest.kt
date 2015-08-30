package io.mstream.boardgameengine.game.tictactoe

import io.mstream.boardgameengine.*
import io.mstream.boardgameengine.game.*
import org.junit.*

class TicTacToeInitializationTest {

    @Test fun shouldCreateSizeOfThreeBoard() {
        val recordingEventSender = RecordingEventSender()
        TicTacToe(recordingEventSender)
        val events = recordingEventSender.receivedEvents
        val boardCreatedEvents = events.filterIsInstance<BoardCreated>()
        Assert.assertEquals("should emit only one BoardCreated event",
                1, boardCreatedEvents.count())
        val boardCreatedEvent = boardCreatedEvents.get(0)
        Assert.assertEquals("created board size should be 3",
                3, boardCreatedEvent.size)
    }

    @Test fun shouldCreateEmptyBoard() {
        val recordingEventSender = RecordingEventSender()
        TicTacToe(recordingEventSender)
        val events = recordingEventSender.receivedEvents
        val pieceSetEvents = events.filterIsInstance<PieceSet>()
        Assert.assertTrue("shouldn't emit any PieceSet event",
                pieceSetEvents.isEmpty())
    }


}