package cleancode.minesweeper.tobe.cell;

public class CellState {

    private boolean isFlagged;
    private boolean isOpened;

    private CellState(boolean isFlagged, boolean isOpened) {
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    public static CellState initialize() {
        return new CellState(false, false);
    }

    //깃발 꽂기
    public void flag() {
        this.isFlagged = true;
    }

    //셀 열기
    public void open() {
        this.isOpened = true;
    }

    //깃발을 꽂았거나 셀이 열렸거나 체크
    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean isFlagged() {
        return isFlagged;
    }
}
