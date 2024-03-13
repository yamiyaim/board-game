package sudoku.shogi;

import sudoku.board.Cell;
import sudoku.shogi.koma.Koma;

public class ShogiCell implements Cell<Koma> {
    private Koma value;

    public Koma getValue() {
        return value;
    }

    public void setValue(Koma koma) {
        value = koma;
    }

    public boolean equals(Cell<Koma> cell) {
        return value == cell.getValue();
    }
}
