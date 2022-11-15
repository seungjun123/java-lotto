package lotto.service;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;
import lotto.domain.LottoNumberGroup;
import lotto.util.Constant;
import lotto.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoService {
    private static int lottoCount;
    private final Validator validator;
    private final LottoNumberGroup lottoNumberGroup;

    public LottoService() {
        validator = new Validator();
        lottoNumberGroup = new LottoNumberGroup();
    }

    public void gameStart() {
        System.out.println(Constant.INPUT_PURCHASE_AMOUNTS_MESSAGE);
    }

    public void inputLottoPurchaseAmounts() {
        String lottoPurchaseAmounts = Console.readLine();
        validator.validatePurchaseAmounts(lottoPurchaseAmounts);
        lottoCount = Integer.parseInt(lottoPurchaseAmounts) / Constant.ONE_THOUSAND;
    }

    public void generateLottoNumbers() {
        List<Integer> uniqueNumbers;
        List<Lotto> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            uniqueNumbers = Randoms.pickUniqueNumbersInRange(Constant.MIN_LOTTO_NUMBER, Constant.MAX_LOTTO_NUMBER, Constant.LOTTO_SIZE);
            Collections.sort(uniqueNumbers);
            lottoNumbers.add(new Lotto(uniqueNumbers));
        }
        lottoNumberGroup.setLottoNumbers(lottoNumbers);
    }

}
