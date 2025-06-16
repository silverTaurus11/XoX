package com.gayuh.xox.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gayuh.xox.utils.DOMINATION_CELL_NUMBER
import com.gayuh.xox.utils.DOMINATION_LOOP_LIMITATION
import com.gayuh.xox.utils.DominationCell
import com.gayuh.xox.utils.DominationGameIntent
import com.gayuh.xox.utils.DominationGameState
import com.gayuh.xox.utils.DominationPlayer

class DominationViewModel: ViewModel() {
    var state by mutableStateOf(DominationGameState())
        private set

    fun sendIntent(intent: DominationGameIntent) {
        when (intent) {
            is DominationGameIntent.MakeMove -> makeMove(intent.row, intent.col)
            is DominationGameIntent.Restart -> state = DominationGameState()
        }
    }

    private fun makeMove(row: Int, col: Int) {
        if (state.isBoardFull || state.board[row][col] != DominationCell.EMPTY) return

        val newBoard = state.board.mapIndexed { r, rowList ->
            rowList.mapIndexed { c, cell ->
                if (r == row && c == col) {
                    if (state.currentPlayer == DominationPlayer.X) DominationCell.X else DominationCell.O
                } else cell
            }
        }

        val updatedState = state.copy(
            board = newBoard,
            currentPlayer = if (state.currentPlayer == DominationPlayer.X) DominationPlayer.O else DominationPlayer.X
        )

        val (scoreX, scoreO) = countScores(newBoard)
        val isBoardFull = newBoard.all { row -> row.all { it != DominationCell.EMPTY } }

        state = updatedState.copy(scoreX = scoreX, scoreO = scoreO, isBoardFull = isBoardFull)
    }

    private fun countScores(board: List<List<DominationCell>>): Pair<Int, Int> {
        var scoreX = 0
        var scoreO = 0

        fun check3(a: DominationCell, b: DominationCell, c: DominationCell): DominationCell? =
            if (a == b && b == c && a != DominationCell.EMPTY) a else null

        for (i in 0 until DOMINATION_CELL_NUMBER) {
            for (j in 0 until DOMINATION_LOOP_LIMITATION) {
                // Horizontal
                when (check3(board[i][j], board[i][j + 1], board[i][j + 2])) {
                    DominationCell.X -> scoreX++
                    DominationCell.O -> scoreO++
                    else -> {}
                }
                // Vertical
                when (check3(board[j][i], board[j + 1][i], board[j + 2][i])) {
                    DominationCell.X -> scoreX++
                    DominationCell.O -> scoreO++
                    else -> {}
                }
            }
        }

        for (i in 0 until DOMINATION_LOOP_LIMITATION) {
            for (j in 0 until DOMINATION_LOOP_LIMITATION) {
                // Diagonal ↘
                when (check3(board[i][j], board[i + 1][j + 1], board[i + 2][j + 2])) {
                    DominationCell.X -> scoreX++
                    DominationCell.O -> scoreO++
                    else -> {}
                }
                // Diagonal ↙
                when (check3(board[i][j + 2], board[i + 1][j + 1], board[i + 2][j])) {
                    DominationCell.X -> scoreX++
                    DominationCell.O -> scoreO++
                    else -> {}
                }
            }
        }

        return scoreX to scoreO
    }
}