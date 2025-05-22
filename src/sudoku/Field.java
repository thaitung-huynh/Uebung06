package sudoku;

import java.util.*;

public class Field {
    private final int x;
    private final int y;
    private Value value;
    private final Sudoku sudoku;
    private final Set<Value> domain = EnumSet.allOf(Value.class);
    private List<Field> dependents = null;

    public Field(int x, int y, Sudoku sudoku) {
        this.x = x;
        this.y = y;
        this.sudoku = sudoku;
    }

    public boolean isEmpty() {
        return value == null;
    }


    public void setValue(Value v) {
        this.value = v;
    }

    public Value getValue() {
        return value;
    }


    public List<Field> getDependents() {
        if (dependents == null) {
            dependents = new ArrayList<>();

            for (int i = 0; i < 9; ++i) {
                // i steht fuer Reihe
                if (i != x) dependents.add(sudoku.getField(i, y));

                // i steht fuer Spalte
                if (i != y) dependents.add(sudoku.getField(x, i));
            }

            // Suche Block 3x3
            int blockX = (x / 3) * 3;
            int blockY = (y / 3) * 3;
            /*
            * 1 2 3
            * 4 5 6
            * 7 8 9
            */
            for (int i = blockX; i < blockX + 3; i++) {
                for (int j = blockY; j < blockY + 3; j++) {
                    if (i != x && j != y) {
                        Field curField = sudoku.getField(i, j);
                        if (!dependents.contains(curField)) dependents.add(curField);
                    }
                }
            }
        }

        return dependents;
    }

    public Set<Value> getDomain() {
        return !isEmpty() ? Collections.emptySet() : domain;
    }

    @Override
    public String toString() {
        return (value == null ? "." : value.toString());
    }
}
