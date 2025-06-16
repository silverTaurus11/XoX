package com.gayuh.xox.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gayuh.xox.R
import com.gayuh.xox.utils.Cell
import com.gayuh.xox.utils.GameEvent
import com.gayuh.xox.utils.GameState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ClassicScreen(
    modifier: Modifier = Modifier,
    stateFlow: StateFlow<GameState>,
    onEvent: (GameEvent) -> Unit
) {
    val state by stateFlow.collectAsState()

    Column(
        modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.title), fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        state.board.forEachIndexed { row, rowList ->
            Row {
                rowList.forEachIndexed { col, cell ->
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .padding(4.dp)
                            .background(Color.LightGray, RoundedCornerShape(8.dp))
                            .clickable {
                                onEvent(GameEvent.MakeMove(Cell(row, col)))
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(cell?.name ?: "", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        when {
            state.winner != null -> Text(
                stringResource(R.string.winner, "${state.winner}"),
                color = Color.Green,
                fontSize = 20.sp
            )

            state.isDraw -> Text(
                stringResource(R.string.draw),
                color = Color.Gray,
                fontSize = 20.sp
            )

            else -> Text(
                stringResource(R.string.turn, "${state.currentPlayer}"),
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onEvent(GameEvent.Restart) }) {
            Text(stringResource(R.string.restart))
        }
    }
}