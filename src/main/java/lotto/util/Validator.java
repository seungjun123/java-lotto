package lotto.util;

public class Validator {
    public void validatePurchaseAmounts(String lottoPurchaseAmounts) {
        validateIsDigit(lottoPurchaseAmounts);
        validateMoreThanOneThousand(lottoPurchaseAmounts);
        for (int i = 1; i < Constant.FOUR_DIGIT; i++) {
            validateOneThousandUnit(lottoPurchaseAmounts.charAt(lottoPurchaseAmounts.length() - i));
        }
    }

    public void validateIsDigit(String lottoPurchaseAmount) {
        try {
            Integer.parseInt(lottoPurchaseAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 정수만 입력 가능합니다.");
        }
    }

    public void validateMoreThanOneThousand(String lottoPurchaseAmounts) {
        if (lottoPurchaseAmounts.length() < Constant.FOUR_DIGIT) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 천 원 이상만 입력 가능합니다.");
        }
    }

    public void validateOneThousandUnit(char lottoPurchaseAmount) {
        if (lottoPurchaseAmount != Constant.ZERO_STRING) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 천 원 단위로만 입력 가능합니다.");
        }
    }
}
