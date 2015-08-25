package io.mstream.boardgameengine.move

import io.mstream.boardgameengine.Position

data class Drag(val from: Position, val to: Position) : Move
