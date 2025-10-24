package guessnumbergamegui;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GuessNumberGameGUI extends JFrame {
    private JTextPane welcomeArea;
    private JTextPane feedbackArea;
    private JTextField inputField;
    private JButton submitButton;
    private JButton resetButton;
    private JLabel attemptsLabel;

    private int secretNumber;
    private int attempts;

    private Image backgroundImage;
    private final LinkedList<String> feedbackMessages = new LinkedList<>();

    private JLabel congratsLabel;
    private JLabel gifLabel;
    private JPanel congratsLabelPanel;
    private JPanel gifLabelPanel;
    private JPanel congratsPanel;

    public GuessNumberGameGUI() {
        super("Guess the Number Game");

        try {
            backgroundImage = ImageIO.read(GuessNumberGameGUI.class.getResource("/images/background.jpg"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Background image not found, using default background.");
            backgroundImage = null;
        }

        setResizable(false);
        setSize(1280, 820);
        setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    int pw = getWidth(), ph = getHeight();
                    int iw = backgroundImage.getWidth(this), ih = backgroundImage.getHeight(this);
                    double pr = (double) pw / ph, ir = (double) iw / ih;

                    int dw, dh, x, y;
                    if (pr > ir) {
                        dw = pw;
                        dh = (int) (pw / ir);
                        x = 0;
                        y = (ph - dh) / 2;
                    } else {
                        dh = ph;
                        dw = (int) (ir * ph);
                        y = 0;
                        x = (pw - dw) / 2;
                    }
                    g.drawImage(backgroundImage, x, y, dw, dh, this);
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Welcome area
        welcomeArea = new JTextPane();
        welcomeArea.setEditable(false);
        welcomeArea.setOpaque(false);
        welcomeArea.setForeground(Color.BLACK);
        welcomeArea.setFont(new Font("SansSerif", Font.BOLD, 20));
        welcomeArea.setPreferredSize(new Dimension(900, 120));
        setCenteredParagraph(welcomeArea);
        welcomeArea.setText("Welcome to Guess the Number!\n\n\nI'm thinking of a number between 1 and 100. Try to guess it!\n");

        // Feedback area
        feedbackArea = new JTextPane();
        feedbackArea.setEditable(false);
        feedbackArea.setOpaque(false);
        feedbackArea.setForeground(Color.BLACK);
        feedbackArea.setFont(new Font("SansSerif", Font.BOLD, 20));
        setCenteredParagraph(feedbackArea);

        JScrollPane scrollPane = new JScrollPane(feedbackArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(900, 200));

        // Congrats message and GIF
        congratsLabel = new JLabel("");
        congratsLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        congratsLabel.setForeground(Color.BLACK);
        congratsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        congratsLabel.setVisible(false);

        gifLabel = new JLabel();
        gifLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gifLabel.setVisible(false);

        congratsLabelPanel = new JPanel();
        congratsLabelPanel.setOpaque(false);
        congratsLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        congratsLabelPanel.add(congratsLabel);
        congratsLabelPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        gifLabelPanel = new JPanel();
        gifLabelPanel.setOpaque(false);
        gifLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        gifLabelPanel.add(gifLabel);
        gifLabelPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        congratsPanel = new JPanel();
        congratsPanel.setOpaque(false);
        congratsPanel.setLayout(new BoxLayout(congratsPanel, BoxLayout.Y_AXIS));
        congratsPanel.add(congratsLabelPanel);
        congratsPanel.add(gifLabelPanel);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createVerticalStrut(75));
        centerPanel.add(welcomeArea);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(scrollPane);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(congratsPanel);

        backgroundPanel.add(centerPanel, BorderLayout.CENTER);

        // Input
        inputField = new JTextField(5);
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        submitButton = new JButton("Submit Guess");
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        Dimension bs = submitButton.getPreferredSize();
        inputField.setPreferredSize(bs);

        JLabel promptLabel = new JLabel("Enter a number (1–100):");
        promptLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        promptLabel.setBorder(BorderFactory.createEmptyBorder(45, 290, 50, -5));

        attemptsLabel = new JLabel("Attempts: 0");
        attemptsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        attemptsLabel.setForeground(Color.BLACK);

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        inputPanel.add(promptLabel);
        inputPanel.add(inputField);
        inputPanel.add(submitButton);
        inputPanel.add(resetButton);
        inputPanel.add(Box.createHorizontalStrut(5));
        inputPanel.add(attemptsLabel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(inputPanel);
        bottomPanel.add(Box.createVerticalStrut(230));

        backgroundPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(backgroundPanel);

        startNewGame();

        submitButton.addActionListener(e -> handleGuess());
        inputField.addActionListener(e -> handleGuess());
        resetButton.addActionListener(e -> startNewGame());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setCenteredParagraph(JTextPane pane) {
        StyledDocument doc = pane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    private void startNewGame() {
        secretNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        feedbackMessages.clear();
        updateAttemptsLabel();
        congratsLabel.setVisible(false);
        gifLabel.setVisible(false);
        feedbackArea.setText("");
        inputField.setText("");
        inputField.setEnabled(true);
        submitButton.setEnabled(true);
        SwingUtilities.invokeLater(() -> inputField.requestFocusInWindow());
    }

    private void handleGuess() {
        String in = inputField.getText().trim();
        try {
            int guess = Integer.parseInt(in);
            attempts++;
            updateAttemptsLabel();

            if (guess == secretNumber) {
                feedbackMessages.clear();

                congratsLabel.setText("<html><div style='text-align:center;'>o(≧▽≦)o Correct! You guessed it in " + attempts + " attempts.<br>✩₊˚.⋆☾⋆⁺₊✧</div></html>");
                congratsLabel.setVisible(true);

                try {
                    ImageIcon gif = new ImageIcon("src/images/dance.gif");
                    Image scaled = gif.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                    gif.setImage(scaled);
                    gifLabel.setIcon(gif);
                    gifLabel.setVisible(true);
                } catch (Exception e) {
                    System.err.println("Failed to load GIF: " + e.getMessage());
                    gifLabel.setVisible(false);
                }

                inputField.setEnabled(false);
                submitButton.setEnabled(false);
                feedbackArea.setText("");

            } else {
                if (feedbackMessages.size() >= 5) {
                    feedbackMessages.clear(); // 🧹 Clear all previous 5 lines
                }

                String feedback = (guess < secretNumber)
                        ? "(╥ω╥) Too low! Try again.\n"
                        : "(⊙_⊙) Too high! Try again.\n";

                feedbackMessages.add(feedback);
                congratsLabel.setVisible(false);
                gifLabel.setVisible(false);
                rebuildFeedbackArea();
            }

        } catch (NumberFormatException ex) {
            if (feedbackMessages.size() >= 5) {
                feedbackMessages.clear();
            }
            feedbackMessages.add("❌ Please enter a valid number.\n");
            congratsLabel.setVisible(false);
            gifLabel.setVisible(false);
            rebuildFeedbackArea();
        }

        inputField.setText("");
        inputField.requestFocus();
    }

    private void rebuildFeedbackArea() {
        feedbackArea.setText("");
        StyledDocument doc = feedbackArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);

        try {
            for (String msg : feedbackMessages) {
                doc.insertString(doc.getLength(), msg, null);
            }
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void updateAttemptsLabel() {
        attemptsLabel.setText("Attempts: " + attempts);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GuessNumberGameGUI::new);
    }
}
