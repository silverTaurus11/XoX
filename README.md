# Dominance Tic Tac Toe

🧠 **Dominance Tic Tac Toe** is a fresh twist on the classic Tic Tac Toe game. Instead of winning with just one row, players compete to create as many `XXX` or `OOO` sequences as possible on a massive 6x6 board. The player with the highest score at the end wins!

## 🎮 Features

- 🔲 **Dominance Mode**:
  - 24x24 board
  - Score based on how many `XXX` or `OOO` you form  
- 🤝 **Player vs Player**
  - Traditional 3x3 board
  - Win with a single straight line (horizontal, vertical, or diagonal)
- 🧠 **Player vs AI**
  - Traditional 3x3 board
  - Win with a single straight line (horizontal, vertical, or diagonal)
- 🔄 Restart game anytime
- 🌐 **Multilingual support**: Indonesian 🇮🇩 & English 🇬🇧
- 🎨 Built with Jetpack Compose
- ✅ Clean **MVI (Model-View-Intent)** architecture

## 🧱 Tech Stack

- Kotlin + Jetpack Compose
- State management with `ViewModel` and `mutableStateOf`
- `LazyVerticalGrid` used for scalable boards
- Manual language switching (via toggle)
- MVI design pattern for clear separation of logic
