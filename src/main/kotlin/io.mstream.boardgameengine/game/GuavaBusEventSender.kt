package io.mstream.boardgameengine.game

import com.google.common.eventbus.*
import io.mstream.boardgameengine.move.*


class GuavaBusEventSender(
        private val id: Int,
        private val eventBus: EventBus) : EventSender {

    override fun post(event: Any) {
        val gameEvent = GameEvent(id, event)
        eventBus.post(gameEvent)
    }
}

