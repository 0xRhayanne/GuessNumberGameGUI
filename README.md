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

git clone https://github.com/yourusername/GuessNumberGameGUI.git

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
