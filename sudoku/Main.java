package sudoku;

import java.util.Scanner;

import sudoku.exception.InputException;
import sudoku.exception.SolveException;
import sudoku.sudoku.SudokuBoard;
import sudoku.sudoku.SudokuBuilder;
import sudoku.sudoku.SudokuSolver;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // 標準入力：「半角スペース区切りの"1"~"9"または"."が９文字」の行が9行
            int size = Integer.parseInt(sc.nextLine());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size * size; i++)
                sb.append(sc.nextLine()).append("\n");

            SudokuBuilder builder = new SudokuBuilder();
            // nと盤面から実の盤面を作る
            SudokuBoard sudoku = builder.build(size, sb.toString());

            // 数独を解いて出力する（この時点でsudokuの中身自体が答えになっている）
            SudokuSolver solver = new SudokuSolver();
            solver.solve(sudoku).print();
        } catch (NumberFormatException e) {
            System.out.println("最初の行には数独の盤面の一片の長さにルートをかけた数字に書いてください。例）9x9の盤面だと3を入力");
        } catch (InputException e) {
            System.out.println(e.getMessage());
        } catch (SolveException e) {
            System.out.println(e.getMessage());
        }
    }
}
