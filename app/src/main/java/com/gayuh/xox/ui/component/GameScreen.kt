package com.gayuh.xox.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gayuh.xox.R
import com.gayuh.xox.utils.GameEvent
import com.gayuh.xox.utils.GameMode
import com.gayuh.xox.utils.GameState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    stateFlow: StateFlow<GameState>,
    onEvent: (GameEvent) -> Unit
) {
    val state by stateFlow.collectAsState()

    if (state.isChoosingMode) {
        ModeSelection(onModeSelected = {
            onEvent(GameEvent.SelectMode(it))
        })
    } else {
        if (state.mode == GameMode.Domination) {
            DominationScreen(onEvent = onEvent)
        } else {
            ClassicScreen(modifier = modifier, stateFlow, onEvent)
        }
    }
}

@Composable
fun ModeSelection(onModeSelected: (GameMode) -> Unit) {
    Column {
        LanguageToggle()
        Column(
            Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(R.string.choose_mode),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onModeSelected(GameMode.PvP) }, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.player_vs_player))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { onModeSelected(GameMode.PvAI) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.player_vs_ai))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { onModeSelected(GameMode.Domination) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.domination))
            }
        }
    }
}
