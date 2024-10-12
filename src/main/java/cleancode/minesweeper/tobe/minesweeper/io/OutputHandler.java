package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.minesweeper.exception.GameException;

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
