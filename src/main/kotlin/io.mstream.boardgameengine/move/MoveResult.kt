package io.mstream.boardgameengine.move

enum class MoveResult {
    CORRECT,
    UNSUPPORTED,
    OUT_OF_BOUNDS,
    FIELD_OCCUPIED;

    fun isCorrect() = this == CORRECT
}
