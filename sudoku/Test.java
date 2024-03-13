package sudoku;

import sudoku.exception.InputException;
import sudoku.exception.SolveException;
import sudoku.sudoku.SudokuBoard;
import sudoku.sudoku.SudokuBuilder;
import sudoku.sudoku.SudokuSolver;

public class Test {
    public static void main(String[] args) {
        try {
            // 標準入力：「半角スペース区切りの"1"~"9"または"."が９文字」の行が9行
            int size = 2; // 後ほどsc.nextInt()に変換、例外処理も生じる
            StringBuilder sb = new StringBuilder();

            sb.append(". . . .").append("\n");
            sb.append(". 4 . .").append("\n");
            sb.append(". . . .").append("\n");
            sb.append("1 . . 3").append("\n");

            SudokuBuilder builder = new SudokuBuilder();
            // nと盤面から実の盤面を作る
            SudokuBoard sudoku = builder.build(size, sb.toString());

            // 数独を解く
            SudokuSolver solver = new SudokuSolver();
            solver.solve(sudoku).print();

            int size2 = 3;
            StringBuilder sb2 = new StringBuilder();

            sb2.append("7 . . . . 5 . . 4").append("\n");
            sb2.append("3 . . 1 . . . . .").append("\n");
            sb2.append(". 8 . . . . . 7 .").append("\n");
            sb2.append(". 9 . . . . 5 . .").append("\n");
            sb2.append(". . . 7 . 4 . 8 1").append("\n");
            sb2.append("5 . 4 . . 2 . . 7").append("\n");
            sb2.append(". . 1 . . . . 3 5").append("\n");
            sb2.append(". . . 4 7 . 2 . .").append("\n");
            sb2.append("4 . . . 9 . . . .").append("\n");

            SudokuBoard sudoku2 = builder.build(size2, sb2.toString());

            solver.solve(sudoku2).print();
        } catch (InputException e) {
            System.out.println(e.getMessage());
        } catch (SolveException e) {
            System.out.println(e.getMessage());
        }
    }
}
