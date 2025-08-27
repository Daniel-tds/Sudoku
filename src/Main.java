import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Sudoku sudoku = new Sudoku();
            new SudokuMenu(sudoku);
        });
    }
}
