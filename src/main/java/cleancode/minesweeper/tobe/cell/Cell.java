package cleancode.minesweeper.tobe.cell;

public interface Cell {

    boolean isLandMine();

    boolean hasLandMineCount();

    CellSnapshot getSnapshot();

    //깃발 꽂기
    void flag();

    //셀 열기
    void open();

    //깃발을 꽂았거나 셀이 열렸거나 체크
    boolean isChecked();

    boolean isOpened();
}
