package sudoku.sudoku;

import java.util.HashSet;
import java.util.Set;

import sudoku.board.BoardChecker;

public class SudokuChecker implements BoardChecker<SudokuBoard> {

    /**
     * 数独の盤面が本当に解答になっているかを試す
     * 矛盾は最後でしか起こり得ないので、最後のインデックスでだけ検証を行う
     * 
     * @param sudoku ：盤面
     * @return trueだと解けている、falseだと解けていない
     */
    public boolean check(SudokuBoard sudoku) {
        int size = sudoku.getSize();
        int max = size * size - 1;

        if (hasDuplicateValueInRow(max, sudoku)
                || hasDuplicateValueInColumn(max, sudoku)
                || hasDuplicateValueInSquare(max, sudoku))
            return false;
        return true;
    }

    /**
     * 数独の盤面のrow番目の行に重複した数は入っていないかを論理型で返す
     * 
     * @param row    ：行の番目数
     * @param sudoku
     * @return 重複値あるとtrue、ないとfalse
     */
    public static boolean hasDuplicateValueInRow(int row, SudokuBoard sudoku) {
        int size = sudoku.getSize();
        int max = size * size;
        Set<Integer> numbers = new HashSet<>();

        for (int i = 0; i < max; i++)
            numbers.add(sudoku.getCellFromRowAndColumn(row, i).getValue());

        if (numbers.size() < max)
            return true;
        return false;
    }

    /**
     * 数独の盤面のcolumn列目の行に重複した数は入っていないかを論理型で返す
     * 
     * @param column ：列の番目数
     * @param sudoku
     * @return 重複値あるとtrue、ないとfalse
     */
    public static boolean hasDuplicateValueInColumn(int column, SudokuBoard sudoku) {
        int size = sudoku.getSize();
        int max = size * size;
        Set<Integer> numbers = new HashSet<>();

        for (int i = 0; i < max; i++)
            numbers.add(sudoku.getCellFromRowAndColumn(i, column).getValue());

        if (numbers.size() < max)
            return true;
        return false;
    }

    /**
     * 数独の盤面のsquare番目の小正四角形に重複した数は入っていないかを論理型で返す
     * 
     * @param square ：小正四角形の番目数
     * @param sudoku
     * @return 重複値あるとtrue、ないとfalse
     */
    public static boolean hasDuplicateValueInSquare(int square, SudokuBoard sudoku) {
        int size = sudoku.getSize();
        int max = size * size;
        Set<Integer> numbers = new HashSet<>();

        for (int i = 0; i < max; i++)
            numbers.add(sudoku.getCellFromSquareNums(square, i).getValue());

        if (numbers.size() < max)
            return true;
        return false;
    }
}
