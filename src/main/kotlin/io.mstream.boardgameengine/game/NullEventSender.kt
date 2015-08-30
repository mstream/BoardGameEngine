package io.mstream.boardgameengine.game


public object NullEventSender : EventSender {
    override fun post(event: Any) {
    }
}