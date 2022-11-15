package lotto.service;

import camp.nextstep.edu.missionutils.Console;
import lotto.util.Constant;
import lotto.util.Validator;

import java.util.ArrayList;
import java.util.Arrays;

public class LottoService {
    private final Validator validator;

    public LottoService() {
        validator = new Validator();
    }

    public void gameStart() {
        System.out.println(Constant.INPUT_PURCHASE_AMOUNTS_MESSAGE);
    }

    public void inputLottoPurchaseAmounts() {
        String lottoPurchaseAmounts = Console.readLine();
        validator.validatePurchaseAmounts(lottoPurchaseAmounts);
    }

}
