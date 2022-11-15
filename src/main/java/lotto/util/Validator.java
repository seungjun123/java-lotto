package lotto.util;

import java.util.List;

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

    public void validateBonusNumber(String bonusNumber, List<Integer> lottoWinningNumber) {
        validateIsDigitBonusNumber(bonusNumber);
        validateDuplicateBonusNumber(bonusNumber, lottoWinningNumber);
        validateNumber(bonusNumber);
    }

    public void validateIsDigitBonusNumber(String bonusNumber) {
        try {
            Integer.parseInt(bonusNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 정수만 입력 가능합니다.");
        }
    }

    public void validateDuplicateBonusNumber(String bonusNumber, List<Integer> lottoWinningNumber) {
        int number = Integer.parseInt(bonusNumber);
        if (lottoWinningNumber.contains(number)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복되면 안 됩니다.");
        }
    }

    public void validateNumber(String bonusNumber) {
        int number = Integer.parseInt(bonusNumber);
        if (number < Constant.MIN_LOTTO_NUMBER || number > Constant.MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }


}
