package io.mstream.boardgameengine.move

enum class MoveResult {
    CORRECT;

    fun isCorrect() = this == CORRECT
}
