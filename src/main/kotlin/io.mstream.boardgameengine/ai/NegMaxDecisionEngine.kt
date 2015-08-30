package io.mstream.boardgameengine.ai

import io.mstream.boardgameengine.board.*
import io.mstream.boardgameengine.game.*
import io.mstream.boardgameengine.move.*

class NegMaxDecisionEngine(private val searchDepth: Int) : DecisionEngine {

    override fun chooseBestMove(game: Game): Move {
        val origin = Node(null, game, 0, getNodeChildren(game))
        val color = if (game.movingSide() == Side.A) 1 else -1
        evaluateMove(origin, searchDepth, color)
        val bestMoveNode = origin.children.filter { node -> node.score == -origin.score }.first()
        return bestMoveNode.move ?: throw IllegalStateException()
    }

    private fun getNodeChildren(game: Game): Set<Node> {
        var children: Set<Node> = emptySet()
        for (move in game.possibleMoves()) {
            val gameAfterMove = game.clone()
            gameAfterMove.makeMove(move)
            children = children.union(
                    setOf(Node(move, gameAfterMove, 0, getNodeChildren(gameAfterMove))))
        }
        return children
    }

    private fun evaluateMove(node: Node, depth: Int, color: Int) {
        when {
            depth == 0 || node.isTerminal() -> {
                val score = color * node.game.evaluation()
                node.score = score
            }
            else                            -> {
                var bestScore = Int.MIN_VALUE
                for (childNode in node.children) {
                    evaluateMove(childNode, depth - 1, -color)
                    bestScore = Math.max(bestScore, -childNode.score)
                }
                node.score = bestScore
            }
        }
    }

    private class Node(
            val move: Move?,
            val game: Game,
            var score: Int,
            val children: Set<Node>
    ) {
        fun isTerminal() = children.isEmpty()
    }
}

