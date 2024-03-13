package sudoku.shogi.koma;

import java.util.ArrayList;
import java.util.List;

import sudoku.shogi.player.ShogiPlayer;
import sudoku.shogi.point.Point;

public class King extends Koma {

    public King(ShogiPlayer player) {
        super(player);
        name = "玉";
    }

    public List<Point> getMovablePointList() {
        List<Point> list = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0)
                    continue;
                list.add(new Point(i, j));
            }
        }
        return list;
    };

    public void promote() {
    }
}
