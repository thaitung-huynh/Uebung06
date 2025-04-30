package sudoku;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {

    private Field[][] board;

    public Sudoku() {
        board = new Field[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) board[i][j] = new Field(i, j, this);
    }

    public void initialize(int... values) {
        for (int idx = 0; idx < values.length; ++idx)
            if (values[idx] != 0) board[idx / 9][idx % 9].setValue(values[idx]);
    }

    public Field getField(int row, int col) {
        return board[row][col];
    }

    public boolean solve() {
        return findNext();
    }

    // solve Hilf-Funktion -- private method
    private boolean findNext() {
        Field bestOption = null;
        int currentBestChoice = 10;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Field f = board[row][col];
                if (f.isEmpty()) {
                    int choice = f.getDomain().size();
                    if (choice < currentBestChoice) {
                        currentBestChoice = choice;
                        bestOption = f;
                    }
                }
            }
        }
        if (bestOption == null) return true;

        for (Value v: bestOption.getDomain()) {
            bestOption.setValue(v);
            if (findNext()) return true;
            bestOption.setValue(null);
        }
        return false;
    }


    public List<Sudoku> solveAll() {
        List<Sudoku> solutions = new ArrayList<>();
        findSolution(solutions);
        return solutions;
    }

    private void findSolution(List<Sudoku> solutions) {
        Field bestOption = null;
        int currentBestChoice = 10;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Field f = board[row][col];
                if (f.isEmpty()) {
                    int choice = f.getDomain().size();
                    if (choice < currentBestChoice) {
                        currentBestChoice = choice;
                        bestOption = f;
                    }
                }
            }
        }
        if (bestOption == null){
            Sudoku newSol = new Sudoku();
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    Field f = board[row][col];
                    newSol.getField(row, col).setValue(f.getValue());
                }
            }
            solutions.add(newSol);
            return;
        }

        for (Value v: bestOption.getDomain()) {
            bestOption.setValue(v);
            findSolution(solutions);
            bestOption.setValue(null);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) sb.append("---------+---------+---------\n");
            sb.append(" ");
            for (int j = 0; j < 9; j++) {
                if (j == 2 || j == 5) sb.append(board[i][j] + " | ");
                else
                    sb.append(board[i][j] + "  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
