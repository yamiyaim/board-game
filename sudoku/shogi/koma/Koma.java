package sudoku.shogi.koma;

import java.util.ArrayList;
import java.util.List;

import sudoku.shogi.player.ShogiPlayer;
import sudoku.shogi.point.Point;

public abstract class Koma {
    protected String name;
    protected String promotedName;
    protected boolean isPromoted;
    protected ShogiPlayer player;

    public Koma(ShogiPlayer player) {
        this.isPromoted = false;
        this.player = player;
    }

    public List<Point> getMovablePointList() {
        List<Point> list = new ArrayList<>();
        return list;
    };

    public void promote() {
        isPromoted = true;
    }

    public String toString() {
        if (isPromoted)
            return promotedName;
        return name;
    }

    public ShogiPlayer getPlayer() {
        return player;
    }
}
