package io.mstream.boardgameengine.move

enum class MoveResult {
    CORRECT,
    UNSUPPORTED,
    OUT_OF_BOUNDS,
    FIELD_OCCUPIED,
    GAME_IS_FINISHED;

    fun isCorrect() = this == CORRECT
}
