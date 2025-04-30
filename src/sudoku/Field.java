package sudoku;

import java.util.*;

public class Field {
    private int x;
    private int y;
    private Value value;
    private Sudoku sudoku;

    private List<Field> dependents;
    private Set<Value> domain;

    public Field(int x, int y, Sudoku sudoku) {
        this.x = x;
        this.y = y;
        this.value = null;
        this.sudoku = sudoku;
        dependents = null;
        domain = null;
    }

    public boolean isEmpty() {
        return value == null;
    }

    public void setValue(int v) {
        this.value = Value.of(v);
    }

    // Overloading
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
            int blockX = (x / 3) * 3; // (x / 3) = int(x div 3). Z.b: (3,3)
            int blockY = (y / 3) * 3;

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
        if (!isEmpty()) return Collections.emptySet(); // ohne NullpointerException
        domain = new HashSet<>();
        // Fuege alle Werte (1 -> 9) hinzu
        for (int v = 1; v <= 9; ++v) domain.add(Value.of(v));

        for (Field dep: getDependents())
            if (!dep.isEmpty()) domain.remove(dep.getValue());
        return domain;
    }

    @Override
    public String toString() {
        return (value == null ? "." : value.toString());
    }
}
