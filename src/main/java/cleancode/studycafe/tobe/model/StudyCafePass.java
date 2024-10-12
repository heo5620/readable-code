package cleancode.studycafe.tobe.model;

import java.util.Set;

//하나의 이용권
public class StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration; //기간
    private final int price; //가격
    private final double discountRate; //할인율

    private StudyCafePass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafePass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafePass(passType, duration, price, discountRate);
    }

    public boolean isSameDurationType(StudyCafeLockerPass lockerPass) {
        return lockerPass.isSamePassType(this.passType) && lockerPass.isSameDuration(this.duration);
    }

    public boolean isSamePassType(StudyCafePassType studyCafePassType) {
        return this.passType == studyCafePassType;
    }

    public StudyCafePassType getPassType() {
        return passType;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public String display() {
        if (passType == StudyCafePassType.HOURLY) {
            return String.format("%s시간권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.WEEKLY) {
            return String.format("%s주권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.FIXED) {
            return String.format("%s주권 - %d원", duration, price);
        }
        return "";
    }

    public boolean cannotUseLocker() {
        return this.passType.isNotLockerType();
    }
}
