package sudoku;

import sudoku.shogi.ShogiBoard;
import sudoku.shogi.ShogiBuilder;
import sudoku.shogi.ShogiChecker;

public class ShogiTest {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        sb.append("1 1 後 香").append("\n");
        sb.append("1 2 後 桂").append("\n");
        sb.append("1 3 後 銀").append("\n");
        sb.append("1 4 後 金").append("\n");
        sb.append("1 5 後 玉").append("\n");
        sb.append("1 6 後 金").append("\n");
        sb.append("1 7 後 銀").append("\n");
        sb.append("1 8 後 桂").append("\n");
        sb.append("1 9 後 香").append("\n");

        sb.append("2 2 後 飛").append("\n");
        sb.append("2 8 後 角").append("\n");

        sb.append("3 1 後 歩").append("\n");
        sb.append("3 2 後 歩").append("\n");
        sb.append("3 3 後 歩").append("\n");
        sb.append("3 4 後 歩").append("\n");
        sb.append("3 5 後 歩").append("\n");
        sb.append("3 6 後 歩").append("\n");
        sb.append("3 7 後 歩").append("\n");
        sb.append("3 8 後 歩").append("\n");
        sb.append("3 9 後 歩").append("\n");

        sb.append("7 1 先 歩").append("\n");
        sb.append("7 2 先 歩").append("\n");
        sb.append("7 3 先 歩").append("\n");
        sb.append("7 4 先 歩").append("\n");
        sb.append("7 5 先 歩").append("\n");
        sb.append("7 6 先 歩").append("\n");
        sb.append("7 7 先 歩").append("\n");
        sb.append("7 8 先 歩").append("\n");
        sb.append("7 9 先 歩").append("\n");

        sb.append("8 2 先 角").append("\n");
        sb.append("8 8 先 飛").append("\n");

        sb.append("9 1 先 香").append("\n");
        sb.append("9 2 先 桂").append("\n");
        sb.append("9 3 先 銀").append("\n");
        sb.append("9 4 先 金").append("\n");
        sb.append("9 5 先 玉").append("\n");
        sb.append("9 6 先 金").append("\n");
        sb.append("9 7 先 銀").append("\n");
        sb.append("9 8 先 桂").append("\n");
        sb.append("9 9 先 香");

        ShogiBuilder builder = new ShogiBuilder();
        ShogiBoard shogi = builder.build(9, sb.toString());

        ShogiChecker checker = new ShogiChecker();
        System.out.println(checker.check(shogi));
        shogi.print();
    }
}
