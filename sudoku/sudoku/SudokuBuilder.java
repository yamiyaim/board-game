package sudoku.sudoku;

import sudoku.board.BoardBuilder;
import sudoku.exception.InputException;

public class SudokuBuilder implements BoardBuilder<SudokuBoard> {

    public SudokuBoard build(int size, String board) {
        int max = size * size;

        // 数独の盤面を用意する
        SudokuCell[][] sudoku = new SudokuCell[max][max];

        String[] splittedBoard = board.split("\n");
        // 入力を取得する
        for (int i = 0; i < max; i++) {
            // 入力を行単位で受ける
            String readLine = splittedBoard[i];

            // 持ってきた行を空白で区切る
            String[] splittedLine = readLine.split(" ");

            // 行数=列数に合わない数のものが出来上がっている場合は例外を出す
            if (splittedLine.length != max)
                throw new InputException("指定された数独の数に合わない長さの行が与えられました");

            for (int j = 0; j < max; j++) {
                String number = splittedLine[j];
                sudoku[i][j] = buildCell(number, max);
            }
        }

        return new SudokuBoard(size, sudoku);
    }

    private SudokuCell buildCell(String number, int max) {
        if (number.equals("."))
            return new SudokuCell();
        try {
            int value = Integer.parseInt(number);
            if (value < 1 || value > max)
                throw new InputException("数独盤の大きさに合わない数字が入力されました");
            return new SudokuCell(value);
        } catch (NumberFormatException e) {
            // Integer.parseIntの部分で数字形式に合わない入力がある場合に例外を出す
            throw new InputException("数字または'.'が正しく入力されませんでした");
        }
    }
}
