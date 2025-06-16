package com.gayuh.xox.viewmodel

import com.gayuh.xox.utils.Cell
import com.gayuh.xox.utils.GameState
import com.gayuh.xox.utils.Player

fun makeMove(state: GameState, cell: Cell): GameState {
    val newBoard = state.board.mapIndexed { r, row ->
        row.mapIndexed { c, cellValue ->
            if (r == cell.row && c == cell.col) state.currentPlayer else cellValue
        }
    }

    val winner = checkWinner(newBoard)
    val draw = winner == null && newBoard.flatten().all { it != null }

    return state.copy(
        board = newBoard,
        currentPlayer = if (state.currentPlayer == Player.X) Player.O else Player.X,
        winner = winner,
        isDraw = draw
    )
}

fun checkWinner(board: List<List<Player?>>): Player? {
    val lines = listOf(
        board[0], board[1], board[2],
        listOf(board[0][0], board[1][0], board[2][0]),
        listOf(board[0][1], board[1][1], board[2][1]),
        listOf(board[0][2], board[1][2], board[2][2]),
        listOf(board[0][0], board[1][1], board[2][2]),
        listOf(board[0][2], board[1][1], board[2][0])
    )
    return lines.firstOrNull { it.all { p -> p != null && p == it[0] } }?.first()
}

fun findBestMove(board: List<List<Player?>>): Cell? {
    val empty = mutableListOf<Cell>()
    for (i in 0..2)
        for (j in 0..2)
            if (board[i][j] == null)
                empty.add(Cell(i, j))
    return empty.randomOrNull()
}
