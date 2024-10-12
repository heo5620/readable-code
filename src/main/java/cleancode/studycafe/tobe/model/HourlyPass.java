package cleancode.studycafe.tobe.model;

public class HourlyPass implements StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration; //기간
    private final int price; //가격
    private final double discountRate; //할인율

    public HourlyPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public HourlyPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new HourlyPass(passType, duration, price, discountRate);
    }

    @Override
    public StudyCafePassType getPassType() {
        return passType;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getPrice() {
        return duration;
    }

    @Override
    public double getDiscountRate() {
        return discountRate;
    }

    @Override
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
}
