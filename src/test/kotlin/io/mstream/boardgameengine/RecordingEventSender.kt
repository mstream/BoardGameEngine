package io.mstream.boardgameengine

import io.mstream.boardgameengine.game.*

class RecordingEventSender : EventSender {

    var receivedEvents: List<Any> = emptyList()

    override fun post(event: Any) {
        receivedEvents += event
    }
}

