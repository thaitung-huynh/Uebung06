package sudoku;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {

    private final Field[][] board = new Field[9][9];;

    public Sudoku() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) board[i][j] = new Field(i, j, this);
    }

    public void initialize(int... values) {
        for (int idx = 0; idx < values.length; ++idx)
            if (values[idx] != 0) {
                int i = idx / 9;
                int j = idx % 9;
                Value e = Value.of(values[idx]);
                board[i][j].setValue(e);
                for(Field f: board[i][j].getDependents())
                    if (f.getDomain().contains(e)) f.getDomain().remove(e);
            }
    }

    public Field getField(int row, int col) {
        return board[row][col];
    }

    // Hilfsfunktion für solve() und solveAll()
    private Field findBestChoice() {
        Field bestChoice = null;
        int currentBest = 1000000;
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

            List<Field> dependents = new ArrayList<>();

            for (Field dependent: bestChoice.getDependents())
                if (dependent.getDomain().contains(v)) {
                    dependent.getDomain().remove(v);
                    dependents.add(dependent);
                }

            bestChoice.setValue(v);

            if (canSolve()) return true;

            // Memset
            bestChoice.setValue(null);
            for (Field dependent: dependents) dependent.getDomain().add(v);

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

    @Override
    public Sudoku clone() {
        Sudoku copy = new Sudoku();
        for (int i = 0; i < 9; ++i)
            for (int j = 0; j < 9; ++j) copy.board[i][j].setValue(this.board[i][j].getValue());

        return copy;
    }

    private void findAllSolution(List<Sudoku> solutions) {
        Field bestChoice = findBestChoice();

        // Keine Stelle mehr
        if (bestChoice == null){
            int cnt = 0;
            for (int i = 0; i < 9; ++i)
                for (int j = 0; j < 9; ++j)
                    if (board[i][j].isEmpty()) cnt++;
            //System.out.println("fucking this sudoku");
            solutions.add(this.clone());
            return;
        }

        // Versuche alle Möglichkeiten für bestChoice
        for (Value v: bestChoice.getDomain()) {
            List<Field> dependents = new ArrayList<>();

            for (Field dependent: bestChoice.getDependents())
                if (dependent.getDomain().contains(v)) {
                    dependent.getDomain().remove(v);
                    dependents.add(dependent);
                }

            bestChoice.setValue(v);

            findAllSolution(solutions);

            // Memset
            bestChoice.setValue(null);
            for (Field dependent: dependents) dependent.getDomain().add(v);

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