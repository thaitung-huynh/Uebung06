package sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuApp {

    /*
    public static void main(String[] args) {


        final Sudoku board = new Sudoku();
        board.initialize(
                4, 5, 0, 0, 0, 0, 2, 0, 0,
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
*/
    public static void main(String[] args) {
        System.out.println("Sudoku Aufgabenblatt:");
        System.out.println();
        final Sudoku boardBlatt = new Sudoku();
        boardBlatt.initialize(4, 5, 0, 0, 0, 0, 2, 0, 0,
                6, 0, 0, 0, 2, 4, 8, 0, 0,
                8, 0, 0, 0, 6, 1, 3, 0, 0,
                0, 9, 0, 4, 0, 0, 0, 5, 0,
                0, 1, 0, 2, 0, 8, 0, 7, 0,
                0, 3, 0, 0, 0, 9, 0, 8, 0,
                0, 0, 7, 1, 4, 0, 0, 0, 8,
                0, 0, 2, 7, 9, 0, 0, 0, 6,
                0, 0, 5, 0, 0, 0, 0, 2, 1);
        printSolution(boardBlatt);

        System.out.println("Sudoku Handzettel:");
        System.out.println();
        final Sudoku boardHandzettel = new Sudoku();
        boardHandzettel.initialize(9, 0, 5, 4, 0, 0, 6, 0, 7,
                0, 0, 0, 9, 0, 7, 0, 0, 0,
                4, 2, 0, 0, 0, 0, 9, 1, 0,
                5, 0, 8, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 6, 5, 0, 0, 0,
                0, 0, 0, 1, 0, 9, 0, 0, 0,
                0, 0, 6, 0, 0, 3, 8, 0, 0,
                0, 0, 0, 0, 8, 0, 0, 2, 6,
                8, 0, 0, 2, 0, 6, 3, 4, 0);
        printSolution(boardHandzettel);

    }

    private static void printSolution(Sudoku sudoku) {
        System.out.println(sudoku);

        System.out.println();
        if (sudoku.solve()) {
            System.out.println("Solution:");
            System.out.println();
            System.out.println(sudoku);
        }
        else
            System.out.println("No solution!");
    }

}
