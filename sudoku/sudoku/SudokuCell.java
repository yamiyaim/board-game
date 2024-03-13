package sudoku.sudoku;

import sudoku.board.Cell;

public class SudokuCell implements Cell<Integer> {
    private Integer value;
    protected final int PSEUDO_VALUE = 0;

    public SudokuCell(Integer value) {
        setValue(value);
    }

    public SudokuCell() {
        setValue(PSEUDO_VALUE);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean equals(Cell<Integer> cell) {
        return value.intValue() == cell.getValue().intValue();
    }

    public boolean equals(int number) {
        return value.intValue() == number;
    }

    public boolean isPseudoValue() {
        return value.intValue() == PSEUDO_VALUE;
    }

}
