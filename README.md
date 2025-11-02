# 🧮 Guess Number Game GUI - Interactive guessing game.

- 🕹️ Features;
- 🧠 Random number generation between 1 and 100;
- 🪄 Real-time feedback (too high / too low);
- 🏆 Win message with celebratory GIF animation;
- 📊 Attempt counter;
- 🖼️ Beautiful background image and clean UI;
- 🧼 Feedback history with auto-clear after 5 messages;
- 💻 GUI built using Java Swing.

---

# 🧰 Project Structure

```
GuessNumberGameGUI/
├── src/
│   └── guessnumbergamegui/
│       └── GuessNumberGameGUI.java
├── images/
│   ├── background.jpg
│   └── dance.gif
├── nbproject/                # NetBeans project files
└── README.md


```
---

# 🚀 Getting Started

✅ Prerequisites

- Java 8+;
- NetBeans IDE (or any IDE supporting Java Swing);
- Git (optional).

---

# 🛠️ How to Run (NetBeans)

1. Clone or download this repository:

```

git clone https://github.com/0xRhayanne/GuessNumberGameGUI.git

```
2. Open the project in NetBeans IDE;
3. Right-click the project in the Projects panel;
4. Select Run (or press Shift + F6).

🎉 The game window will appear with the background image and input field ready for your guesses.
---

# 🖼️ Gameplay Preview

When you guess incorrectly:

```
(⊙_⊙) Too high! Try again.
(╥ω╥) Too low! Try again.
❌ Please enter a valid number.
```

When you guess correctly:

```
o(≧▽≦)o Correct! You guessed it in X attempts.
✩₊˚.⋆☾⋆⁺₊✧
```
The dancing GIF (dance.gif) will appear to celebrate your win 🪩.

---

# 🧭 How It Works

On startup, the program generates a random number between 1 and 100.
The user types a guess into the input field and clicks Submit.

The game:

- Increments the attempt counter;
- Gives feedback (too high / too low).

And if guessed correctly:

- Displays a congratulatory message 🎉;
- Shows a dancing animation 🕺;
- Disables further input until Reset is pressed.

---

# 🪄 Resetting the Game

Click "Reset Game" to:

- Generate a new secret number;
- Reset attempts to 0;
- Clear feedback messages;
- Hide the GIF animation.

---

# 🖼️ Customizing the UI

You can easily modify the look of the game:

- Replace ```images/background.jpg``` with your own background;
- Replace ```images/dance.gif``` with your own celebration animation;
- Change colors, fonts, or layout directly in ```GuessNumberGameGUI.java```
---

# 🐞 Known Limitations

- The window size is fixed (```1280x820```);
- The game only supports integers between 1 and 100;
- If the background image is missing, the game still runs with a default background.
---

# 🧭 Future Improvements (Ideas)

- ⌨️ Keyboard shortcuts (e.g., Enter to reset);
- 🌈 Theme customization (dark/light mode);
- 🏅 Scoreboard or history of games;
- 🔊 Sound effects for guesses and wins;
- 🌍 Multi-language support.
---

# 🙌 Acknowledgments

- 🖼️ UI built with Java Swing;
- 🧠 Inspired by classic number guessing games;
- ✨ Made using NetBeans IDE.
