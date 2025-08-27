# Sudoku

Projeto em Java de Sudoku com interface gráfica em Swing.

## Diagrama de Classes (tentativa)

```mermaid
classDiagram
    class Sudoku {
        + iniciarJogo()
        + colocarNumero()
        + removerNumero()
        + podeInserir()
        + verificarStatus()
        + limpar()
        + finalizar()
    }

    class SudokuMenu {
        + atualizarTabuleiro()
        + iniciarInterface()
        + criarBotoes()
    }

    class Main {
        + main()
    }

    Main --> Sudoku
    Main --> SudokuMenu
    SudokuMenu --> Sudoku
