package com.gayuh.xox.viewmodel

import androidx.lifecycle.ViewModel
import com.gayuh.xox.utils.GameEvent
import com.gayuh.xox.utils.GameMode
import com.gayuh.xox.utils.GameState
import com.gayuh.xox.utils.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TicTacToeViewModel : ViewModel() {
    private val _state = MutableStateFlow(GameState())
    val state: StateFlow<GameState> = _state

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.SelectMode -> {
                _state.value = GameState(mode = event.mode, isChoosingMode = false)
            }
            is GameEvent.MakeMove -> {
                val current = _state.value
                if (current.winner != null || current.board[event.cell.row][event.cell.col] != null) return

                val newState = makeMove(current, event.cell)
                _state.value = newState

                if (newState.mode == GameMode.PvAI &&
                    newState.currentPlayer == Player.O &&
                    newState.winner == null && !newState.isDraw
                ) {
                    val aiMove = findBestMove(newState.board)
                    if (aiMove != null) {
                        _state.value = makeMove(_state.value, aiMove)
                    }
                }
            }
            GameEvent.Restart -> {
                _state.value = GameState()
            }
        }
    }
}
