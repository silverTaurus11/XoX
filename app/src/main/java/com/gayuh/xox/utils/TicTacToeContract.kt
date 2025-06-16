package com.gayuh.xox.utils

enum class Player { X, O }
enum class GameMode { PvP, PvAI, Domination }

data class Cell(val row: Int, val col: Int)

data class GameState(
    val board: List<List<Player?>> = List(3) { List(3) { null } },
    val currentPlayer: Player = Player.X,
    val winner: Player? = null,
    val isDraw: Boolean = false,
    val mode: GameMode = GameMode.PvP,
    val isChoosingMode: Boolean = true
)

sealed interface GameEvent {
    data object Restart : GameEvent
    data class SelectMode(val mode: GameMode) : GameEvent
    data class MakeMove(val cell: Cell) : GameEvent
}
