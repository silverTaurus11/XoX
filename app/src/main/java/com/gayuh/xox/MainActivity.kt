package com.gayuh.xox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gayuh.xox.ui.component.GameScreen
import com.gayuh.xox.ui.theme.XoXTheme
import com.gayuh.xox.viewmodel.TicTacToeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XoXTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding()
                ) { innerPadding ->
                    val viewModel: TicTacToeViewModel = viewModel()
                    GameScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel.state,
                        viewModel::onEvent
                    )
                }
            }
        }
    }
}