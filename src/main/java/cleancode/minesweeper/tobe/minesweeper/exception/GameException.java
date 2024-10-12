package cleancode.minesweeper.tobe.minesweeper.exception;

public class GameException extends RuntimeException {
    public GameException(String message) {
        super(message);
    }

    /*
     * 주문 검증 메서드
     * 1. 주문 항목이 0 이상이어야 한다.
     * 2. 총 금액이 0 이상이어야 한다.
     * 3. 주문을 하는 사용자 정보가 있어야 한다.
     */
    /*
    public boolean validateOrder(Order order) {
        return hasItems(order) && hasCustomerInfo(order) && isTotalPriceInvalid(order);
    }

    private static boolean hasItems(Order order) {
        if (order.getItems().size() > 0) {
            return true;
        }
        log.info("주문 항목이 없습니다.");
        return false;
    }

    private static boolean isTotalPriceInvalid(Order order) {
        if (order.getTotalPrice() > 0) {
            return true;
        }
        log.info("올바르지 않은 총 가격입니다.");
        return false;
    }

    private static boolean hasCustomerInfo(Order order) {
        if (order.hasCustomerInfo()) {
            return true;
        }
        log.info("사용자 정보가 없습니다.");
        return false;
    }
     */

    /*
    public boolean validateOrder(Order order) {
        if (hasItems(order)) return false;

        if (hasCustomerInfo(order)) return false;

        if (isTotalPriceInvalid(order)) return false;
        return true;
    }

    private static boolean isTotalPriceInvalid(Order order) {
        if (!(order.getTotalPrice() > 0)) {
            log.info("올바르지 않은 총 가격입니다.");
            return true;
        }
        return false;
    }

    private static boolean hasCustomerInfo(Order order) {
        if (!order.hasCustomerInfo()) {
            log.info("사용자 정보가 없습니다.");
            return true;
        }
        return false;
    }

    private static boolean hasItems(Order order) {
        if (order.getItems().size() == 0) {
            log.info("주문 항목이 없습니다.");
            return true;
        }
        return false;
    }
     */
}