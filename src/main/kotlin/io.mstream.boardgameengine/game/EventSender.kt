package io.mstream.boardgameengine.game

interface EventSender {

    fun post(event: Any)
}

