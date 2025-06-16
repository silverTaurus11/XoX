package com.gayuh.xox.utils

const val DOMINATION_CELL_NUMBER = 6
const val DOMINATION_LOOP_LIMITATION = (DOMINATION_CELL_NUMBER - 2)

enum class DominationCell { EMPTY, X, O }
enum class DominationPlayer { X, O }

data class DominationGameState(
    val board: List<List<DominationCell>> = List(DOMINATION_CELL_NUMBER) { List(DOMINATION_CELL_NUMBER) { DominationCell.EMPTY } },
    val currentPlayer: DominationPlayer = DominationPlayer.X,
    val scoreX: Int = 0,
    val scoreO: Int = 0,
    val isBoardFull: Boolean = false
)

sealed class DominationGameIntent {
    data class MakeMove(val row: Int, val col: Int) : DominationGameIntent()
    data object Restart : DominationGameIntent()
}