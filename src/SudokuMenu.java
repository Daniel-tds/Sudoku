import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SudokuMenu extends JFrame {
    private final Sudoku sudoku;
    private final JTextField[][] cells = new JTextField[9][9];

    public SudokuMenu(Sudoku sudoku) {
        this.sudoku = sudoku;
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        Color borderColor = Color.BLUE;

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("Arial", Font.BOLD, 20));
                int value = sudoku.getBoard()[r][c];
                cell.setText(value == 0 ? "" : String.valueOf(value));
                cell.setEditable(value == 0);
                if (value != 0) cell.setBackground(Color.LIGHT_GRAY);

                int top = (r % 3 == 0) ? 3 : 1;
                int left = (c % 3 == 0) ? 3 : 1;
                int bottom = (r == 8) ? 3 : 1;
                int right = (c == 8) ? 3 : 1;
                Border border = new MatteBorder(top, left, bottom, right, borderColor);
                cell.setBorder(border);

                final int row = r, col = c;
                cell.addActionListener(e -> {
                    String text = cell.getText();
                    if (!text.matches("[1-9]")) {
                        cell.setText("");
                        return;
                    }
                    int val = Integer.parseInt(text);
                    if (!sudoku.colocarNumero(row, col, val)) {
                        cell.setText("");
                        JOptionPane.showMessageDialog(this, "Número inválido ou posição fixa!");
                    } else {
                        atualizarTabuleiro();
                    }
                });

                cells[r][c] = cell;
                gridPanel.add(cell);
            }
        }

        JPanel buttonsPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        JButton novoBtn = new JButton("Novo Jogo");
        novoBtn.addActionListener(e -> {
            sudoku.iniciarJogo();
            atualizarTabuleiro();
        });
        buttonsPanel.add(novoBtn);

        JButton limparBtn = new JButton("Limpar");
        limparBtn.addActionListener(e -> {
            sudoku.limpar();
            atualizarTabuleiro();
        });
        buttonsPanel.add(limparBtn);

        JButton verificarBtn = new JButton("Verificar");
        verificarBtn.addActionListener(e -> {
            String status = sudoku.verificarStatus();
            JOptionPane.showMessageDialog(this, status);
        });
        buttonsPanel.add(verificarBtn);

        JButton finalizarBtn = new JButton("Finalizar");
        finalizarBtn.addActionListener(e -> {
            if (sudoku.finalizar())
                JOptionPane.showMessageDialog(this, "Parabéns! Sudoku completo e correto!");
            else
                JOptionPane.showMessageDialog(this, "Sudoku não está completo ou contém erros.");
        });
        buttonsPanel.add(finalizarBtn);

        JButton sairBtn = new JButton("Sair");
        sairBtn.addActionListener(e -> System.exit(0));
        buttonsPanel.add(sairBtn);

        add(gridPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.EAST);

        setSize(800, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void atualizarTabuleiro() {
        int[][] board = sudoku.getBoard();
        for (int r = 0; r < 9; r++)
            for (int c = 0; c < 9; c++) {
                cells[r][c].setText(board[r][c] == 0 ? "" : String.valueOf(board[r][c]));
                cells[r][c].setEditable(board[r][c] == 0);
                cells[r][c].setBackground(board[r][c] == 0 ? Color.WHITE : Color.LIGHT_GRAY);
            }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SudokuMenu(new Sudoku()));
    }
}