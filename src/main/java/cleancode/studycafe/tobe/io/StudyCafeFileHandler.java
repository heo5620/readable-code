package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeFileHandler {

    public List<StudyCafePass> readStudyCafePasses() {
        try {
            List<String> lines = readFileFrom("src/main/resources/cleancode/studycafe/pass-list.csv");
            return generateStudyCafePasses(lines);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    public List<StudyCafeLockerPass> readLockerPasses() {
        try {
            List<String> lines = readFileFrom("src/main/resources/cleancode/studycafe/locker.csv");
            return generateStudyCafeLockerPasses(lines);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    private List<String> readFileFrom(String path) throws IOException {
        return Files.readAllLines(Paths.get(path));
    }

    private List<StudyCafePass> generateStudyCafePasses(List<String> lines) {
        List<StudyCafePass> studyCafePasses = new ArrayList<>();
        for (String line : lines) {
            StudyCafePass extractedStudyCafePass = extractStudyCafePass(line);
            studyCafePasses.add(extractedStudyCafePass);
        }
        return studyCafePasses;
    }

    private List<StudyCafeLockerPass> generateStudyCafeLockerPasses(List<String> lines) {
        List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
        for (String line : lines) {
            StudyCafeLockerPass extractStudyCafeLockerPass = extractStudyCafeLockerPass(line);
            lockerPasses.add(extractStudyCafeLockerPass);
        }
        return lockerPasses;
    }

    private static StudyCafePass extractStudyCafePass(String line) {
        String[] values = line.split(",");
        StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
        int duration = Integer.parseInt(values[1]);
        int price = Integer.parseInt(values[2]);
        double discountRate = Double.parseDouble(values[3]);

        return switch (studyCafePassType) {
            case HOURLY -> new HourlyPass(studyCafePassType, duration, price, discountRate);
            case WEEKLY -> new WeeklyPass(studyCafePassType, duration, price, discountRate);
            case FIXED -> new FixedPass(studyCafePassType, duration, price, discountRate);
        };
    }

    private StudyCafeLockerPass extractStudyCafeLockerPass(String line) {
        String[] values = line.split(",");
        StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
        int duration = Integer.parseInt(values[1]);
        int price = Integer.parseInt(values[2]);

        return StudyCafeLockerPass.of(studyCafePassType, duration, price);
    }

}
