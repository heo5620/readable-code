package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.GameBoard;
import cleancode.minesweeper.tobe.GameException;

public interface OutputHandler {
    //게임 초기 메시지 출력
     void showGameStartComments();

     void showBoard(GameBoard board);

     void showGameWinningComment();

     void showGameLosingComment();

     void showCommentForSelectingCell();

     void showCommentForUserAction();

     void showExceptionMessage(GameException e);

     void SHOWSimpleMessage(String message);
}
