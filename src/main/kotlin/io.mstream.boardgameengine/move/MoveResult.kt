package io.mstream.boardgameengine.move

enum class MoveResult {
    CORRECT,
    UNSUPPORTED_MOVE;

    fun isCorrect() = this == CORRECT
}
