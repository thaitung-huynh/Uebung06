package sudoku;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {

    private Field[][] board = new Field[9][9];;

    public Sudoku() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) board[i][j] = new Field(i, j, this);
    }

    public void initialize(int... values) {
        try {
            for (int idx = 0; idx < values.length; ++idx)
                if (values[idx] != 0) {
                    int i = idx / 9;
                    int j = idx % 9;
                    Value e = Value.of(values[idx]);

                    if (!board[i][j].getDomain().contains(e))
                        throw new IllegalArgumentException();

                    board[i][j].setValue(Value.of(values[idx]));
                }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Invalid Board");
        }
    }

    public Field getField(int row, int col) {
        return board[row][col];
    }

    // Hilfsfunktion für solve() und solveAll()
    private Field findBestChoice() {
        Field bestChoice = null;
        int currentBest = 10;
        for (Field[] row : board)
            for (Field f : row) {
                if (f.isEmpty()) {
                    // sizeOfDomain ist die Anzahl der Möglichkeiten, eine neue Zahl in board[i][j] zu setzen.
                    int sizeOfDomain = f.getDomain().size();
                    if (sizeOfDomain < currentBest) {
                        currentBest = sizeOfDomain;
                        bestChoice = f;
                    }
                }
            }

        return bestChoice;
    }

    // Hilfsfunktion für solve()
    private boolean canSolve() {
        Field bestChoice = findBestChoice();
        if (bestChoice == null) return true;

        for (Value v: bestChoice.getDomain()) {
            bestChoice.setValue(v);
            if (canSolve()) return true;
            bestChoice.setValue(null);
        }
        return false;
    }

    public boolean solve() {
        return canSolve();
    }

    public List<Sudoku> solveAll() {
        List<Sudoku> solutions = new ArrayList<>();
        findAllSolution(solutions);
        return solutions;
    }

    private void findAllSolution(List<Sudoku> solutions) {
        Field bestChoice = findBestChoice();

        // Keine Stelle mehr
        if (bestChoice == null){
            solutions.add(this);
            return;
        }

        // Versuche alle Möglichkeiten für bestChoice
        for (Value v: bestChoice.getDomain()) {
            bestChoice.setValue(v);
            findAllSolution(solutions);
            bestChoice.setValue(null);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) sb.append("---------+---------+---------\n");
            sb.append(" ");
            for (int j = 0; j < 9; j++) {
                if (j == 2 || j == 5) sb.append(board[i][j]).append(" | ");
                else
                    sb.append(board[i][j]).append("  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
// Tung Huynh