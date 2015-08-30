package io.mstream.boardgameengine.ai

import io.mstream.boardgameengine.game.*
import io.mstream.boardgameengine.move.*


public interface DecisionEngine {

    fun chooseBestMove(game: Game): Move
}