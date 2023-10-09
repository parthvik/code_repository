import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private JLabel statusLabel;
    private boolean playerX;
    private int numMoves;
//Parthvik was here and wants to chaange this
    public TicTacToParth() {
        // Set up the game board
        buttons = new JButton[3][3];
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 80));
                buttons[row][col].addActionListener(this);
                boardPanel.add(buttons[row][col]);
            }
        }

        // Set up the status label
        statusLabel = new JLabel("Player X's turn");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add the components to the frame
        add(boardPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        // Set up the frame
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Initialize the game
        playerX = true;
        numMoves = 0;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().equals("")) {
            if (playerX) {
                button.setText("X");
                statusLabel.setText("Player O's turn");
            } else {
                button.setText("O");
                statusLabel.setText("Player X's turn");
            }
            playerX = !playerX;
            numMoves++;
            checkForWin();
        }
    }

    private void checkForWin() {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                buttons[row][1].getText().equals(buttons[row][2].getText()) &&
                !buttons[row][0].getText().equals("")) {
                endGame(buttons[row][0].getText() + " wins!");
                return;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                buttons[1][col].getText().equals(buttons[2][col].getText()) &&
                !buttons[0][col].getText().equals("")) {
                endGame(buttons[0][col].getText() + " wins!");
                return;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText()) &&
            !buttons[0][0].getText().equals("")) {
            endGame(buttons[0][0].getText() + " wins!");
            return;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText()) &&
            !buttons[0][2].getText().equals("")) {
            endGame(buttons[0][2].getText() + " wins!");
            return;
        }

        // Check for tie
        if (numMoves == 9) {
            endGame("Tie game!");
            return;
        }

        // Continue the game
        if (playerX) {
            statusLabel.setText("Player X's turn");
        } else {
            statusLabel.setText("Player O's turn");
        }
    }

    private void endGame(String message) {
        JOptionPane.showMessageDialog(this, message);
        System.exit(0);
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
