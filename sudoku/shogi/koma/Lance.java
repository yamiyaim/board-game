package sudoku.shogi.koma;

import java.util.ArrayList;
import java.util.List;

import sudoku.shogi.player.ShogiPlayer;
import sudoku.shogi.point.Point;

public class Lance extends Koma {

    public Lance(ShogiPlayer player) {
        super(player);
        name = "香";
        promotedName = "成香";
    }

    public List<Point> getMovablePointList() {
        List<Point> list = new ArrayList<>();
        if (!isPromoted) {
            for (int i = 1; i < 9; i++) {
                list.add(new Point(i, 0));
            }
        } else {
            list.add(new Point(-1, -1));
            list.add(new Point(-1, 0));
            list.add(new Point(-1, 1));
            list.add(new Point(0, -1));
            list.add(new Point(0, 1));
            list.add(new Point(1, 0));
        }
        return list;
    };
}
