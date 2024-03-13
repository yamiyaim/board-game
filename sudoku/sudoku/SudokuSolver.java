package sudoku.sudoku;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import sudoku.board.BoardSolver;
import sudoku.exception.SolveException;

public class SudokuSolver implements BoardSolver<SudokuBoard> {

    public SudokuBoard solve(SudokuBoard sudoku) {
        return insertNumber(0, sudoku);
    }

    /**
     * 再帰関数
     * 
     * @param index
     * @param sudoku
     * @return　インデックス番目のマスまで進んだ時の盤面
     * @throws SolveException
     */
    private static SudokuBoard insertNumber(int index, SudokuBoard sudoku) {
        if (index == sudoku.getMaxIndex()) {
            // checkに成功すれば現在の盤面を返し、それ以外はSolveExceptionの例外を出す
            SudokuChecker checker = new SudokuChecker();
            if (checker.check(sudoku))
                return sudoku;
            throw new SolveException("失敗");
        }

        // セルの値が変更不可なら、次のindexに移行する
        if (!sudoku.isCellUpdatable(index))
            return insertNumber(index + 1, sudoku);

        // 最適化の一つとして、今の行と今の列と今の小四角形から残りの数字だけを持ってくる
        int[] remain = getRemain(index, sudoku);
        for (int number : remain) {
            sudoku.setCell(index, new SudokuCell(number));

            // まずは次のindexに渡してみてSolveExceptionが起きたら数字を擬似値に戻し、例外が発生しなければそのまま進む
            try {
                return insertNumber(index + 1, sudoku);
            } catch (SolveException e) {
                sudoku.setCell(index, new SudokuCell());
            }
        }

        // 解が見つからない場合
        throw new SolveException("解が存在しません");
    }

    /**
     * 行番号と列番号から行と列に同時に含まれていない数字を探す
     * 小四角形のところまで入れるともっと最適化できるかもしれない（保留）
     * 
     * @param rowNumber    ：行番号
     * @param columnNumber ：列番号
     * @param board        ：今の盤面
     * @return
     */
    private static int[] getRemain(int index, SudokuBoard sudoku) {
        int size = sudoku.getSize();
        int rowNumber = sudoku.getRowNumOfCell(index);
        int columnNumber = sudoku.getColumnNumOfCell(index);
        int squareNumber = rowNumber / size * size + columnNumber / size;

        Set<Integer> numbers = new HashSet<>();

        for (int i = 0; i < size * size; i++) {
            numbers.add(sudoku.getCellFromRowAndColumn(rowNumber, i).getValue());
            numbers.add(sudoku.getCellFromRowAndColumn(i, columnNumber).getValue());
            numbers.add(sudoku.getCellFromSquareNums(squareNumber, i).getValue());
        }

        return IntStream.range(1, size * size + 1).filter(num -> !numbers.contains(num)).toArray();
    }
}
