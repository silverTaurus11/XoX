package com.gayuh.xox.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gayuh.xox.R
import com.gayuh.xox.utils.DOMINATION_CELL_NUMBER
import com.gayuh.xox.utils.DominationCell
import com.gayuh.xox.utils.DominationGameIntent
import com.gayuh.xox.utils.GameEvent
import com.gayuh.xox.viewmodel.DominationViewModel

@Composable
fun DominationScreen(
    modifier: Modifier = Modifier,
    viewModel: DominationViewModel = viewModel(),
    onEvent: (GameEvent) -> Unit
) {
    val state = viewModel.state

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Score X: ${state.scoreX}", fontWeight = FontWeight.Bold)
            Text("Score O: ${state.scoreO}", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(DOMINATION_CELL_NUMBER),
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(state.board.flatten()) { index, cell ->
                val row = index / DOMINATION_CELL_NUMBER
                val col = index % DOMINATION_CELL_NUMBER
                GameCell(cell = cell) {
                    viewModel.sendIntent(DominationGameIntent.MakeMove(row, col))
                }
            }
        }

        if (state.isBoardFull) {
            Text(
                text = when {
                    state.scoreX > state.scoreO -> stringResource(R.string.winner, "X")
                    state.scoreO > state.scoreX -> stringResource(R.string.winner, "0")
                    else -> stringResource(R.string.draw)
                },
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.turn, "${state.currentPlayer}"),
                fontSize = 18.sp
            )
        }
        Button(
            onClick = {
                viewModel.sendIntent(DominationGameIntent.Restart)
                onEvent(GameEvent.Restart)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
        ) {
            Text(stringResource(R.string.restart))
        }
    }
}


@Composable
fun GameCell(cell: DominationCell, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(20.dp)
            .border(BorderStroke(0.5.dp, Color.Gray))
            .clickable(enabled = cell == DominationCell.EMPTY) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = when (cell) {
                DominationCell.X -> "X"
                DominationCell.O -> "O"
                else -> ""
            }, fontSize = 12.sp
        )
    }
}