package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            //1. 스터디 사용권 타입 선택
            outputHandler.askPassTypeSelection(); //타입 목록 출력
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            //2. 파일 읽어서 리스트 추출
            List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();

            //3. 스터디 사용권 타입별 분기문
            switch (studyCafePassType) {
                case HOURLY -> selectHourlyPassType(studyCafePasses);
                case WEEKLY -> selectWeeklyPassType(studyCafePasses);
                case FIXED -> selectFixedPassType(studyCafePasses);
            }
            if (studyCafePassType == StudyCafePassType.HOURLY) { //시간권
                selectHourlyPassType(studyCafePasses);
            } else if (studyCafePassType == StudyCafePassType.WEEKLY) { //주간권
                selectWeeklyPassType(studyCafePasses);
            } else if (studyCafePassType == StudyCafePassType.FIXED) { //고정석
                selectFixedPassType(studyCafePasses);
            }

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void selectFixedPassType(List<StudyCafePass> studyCafePasses) {
        List<StudyCafePass> fixedPasses = studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.FIXED)
            .toList();

        outputHandler.showPassListForSelection(fixedPasses);

        StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);

        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
        StudyCafeLockerPass lockerPass = lockerPasses.stream()
            .filter(option ->
                option.getPassType() == selectedPass.getPassType()
                    && option.getDuration() == selectedPass.getDuration()
            )
            .findFirst()
            .orElse(null);

        boolean lockerSelection = false;
        if (lockerPass != null) {
            outputHandler.askLockerPass(lockerPass);
            lockerSelection = inputHandler.getLockerSelection();
        }

        if (lockerSelection) {
            outputHandler.showPassOrderSummary(selectedPass, lockerPass);
        } else {
            outputHandler.showPassOrderSummary(selectedPass, null);
        }
    }

    private void selectWeeklyPassType(List<StudyCafePass> studyCafePasses) {
        List<StudyCafePass> weeklyPasses = studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.WEEKLY)
            .toList();

        outputHandler.showPassListForSelection(weeklyPasses);

        StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses);

        outputHandler.showPassOrderSummary(selectedPass, null);
    }

    private void selectHourlyPassType(List<StudyCafePass> studyCafePasses) {
        //4. 각 타입별 이용권만 필터링하여 리스트로 반환
        List<StudyCafePass> hourlyPasses = studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.HOURLY)
            .toList();

        //5. 사용자에게 이용권 목록 출력
        outputHandler.showPassListForSelection(hourlyPasses);

        //6. 사용자가 이용권 선택
        StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);

        //7. 선택한 이용권 출력
        outputHandler.showPassOrderSummary(selectedPass, null);
    }

}
