package sudoku;

import java.util.List;

public class SudokuApp {
    public static void main(String[] args) {


        final Sudoku board = new Sudoku();
        board.initialize(4, 5, 0, 0, 0, 0, 2, 0, 0,
                6, 0, 0, 0, 2, 4, 8, 0, 0,
                8, 0, 0, 0, 6, 1, 3, 0, 0,
                0, 9, 0, 4, 0, 0, 0, 5, 0,
                0, 1, 0, 2, 0, 8, 0, 7, 0,
                0, 3, 0, 0, 0, 9, 0, 8, 0,
                0, 0, 7, 1, 4, 0, 0, 0, 8,
                0, 0, 2, 7, 9, 0, 0, 0, 6,
                0, 0, 5, 0, 0, 0, 0, 2, 1);

        System.out.println(board);
        board.solve();

        List<Sudoku> sols = board.solveAll();
        System.out.println(sols);


    }

}
