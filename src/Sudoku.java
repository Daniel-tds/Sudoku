public class Sudoku {
    private final int[][] initialBoard = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };

    private int[][] board = new int[9][9];

    public Sudoku() {
        iniciarJogo();
    }

    public int[][] getBoard() {
        return board;
    }

    public void iniciarJogo() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                board[i][j] = initialBoard[i][j];
    }

    public boolean colocarNumero(int linha, int coluna, int valor) {
        if (valor < 1 || valor > 9) return false; // só permite 1-9
        if (initialBoard[linha][coluna] != 0) return false; // não sobrescreve número fixo
        if (!podeInserir(linha, coluna, valor)) return false;
        board[linha][coluna] = valor;
        return true;
    }

    public boolean podeInserir(int row, int col, int num) {
        // verifica linha
        for (int i = 0; i < 9; i++) {
            if (i != col && board[row][i] == num) return false;
        }
        // verifica coluna
        for (int i = 0; i < 9; i++) {
            if (i != row && board[i][col] == num) return false;
        }
        // verifica bloco 3x3
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if ((r != row || c != col) && board[r][c] == num) return false;
            }
        }
        return true;
    }

    public String verificarStatus() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int val = board[r][c];
                // Se houver célula vazia
                if (val == 0) return "Incompleto";

                // Verifica se o número conflita com outros na linha/coluna/bloco
                for (int i = 0; i < 9; i++) {
                    if (i != c && board[r][i] == val) return "Erro: número inválido em (" + r + "," + c + ")";
                    if (i != r && board[i][c] == val) return "Erro: número inválido em (" + r + "," + c + ")";
                }
                int startRow = (r / 3) * 3;
                int startCol = (c / 3) * 3;
                for (int rr = startRow; rr < startRow + 3; rr++) {
                    for (int cc = startCol; cc < startCol + 3; cc++) {
                        if ((rr != r || cc != c) && board[rr][cc] == val)
                            return "Erro: número inválido em (" + r + "," + c + ")";
                    }
                }
            }
        }
        return "Completo";
    }


    public void limpar() {
        for (int r = 0; r < 9; r++)
            for (int c = 0; c < 9; c++)
                if (initialBoard[r][c] == 0) board[r][c] = 0;
    }

    public boolean finalizar() {
        return verificarStatus().equals("Completo");
    }
}
