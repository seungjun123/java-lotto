package lotto.service;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;
import lotto.domain.LottoNumberGroup;
import lotto.domain.LottoWinningCriteria;
import lotto.domain.WinningNumberGroup;
import lotto.util.Constant;
import lotto.util.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LottoService {
    private static int lottoCount;
    private static List<Integer> winningCount;
    private final Validator validator;
    private final LottoNumberGroup lottoNumberGroup;
    private final WinningNumberGroup winningNumberGroup;

    public LottoService() {
        winningCount = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
        validator = new Validator();
        lottoNumberGroup = new LottoNumberGroup();
        winningNumberGroup = new WinningNumberGroup();
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

    public void printLottoNumbers() {
        System.out.println(System.lineSeparator() + lottoCount + Constant.LOTTO_COUNT_MESSAGE);
        List<Lotto> lottoNumbers = lottoNumberGroup.getLottoNumbers();
        for (Lotto lottoNumber : lottoNumbers) {
            System.out.println(lottoNumber.getNumbers());
        }
    }

    public void inputLottoWinningNumber() {
        System.out.println(System.lineSeparator() + Constant.INPUT_WINNING_NUMBER_MESSAGE);
        String lottoWinningNumbers = Console.readLine();
        Lotto lotto = new Lotto(parseStringToList(lottoWinningNumbers));
        winningNumberGroup.setWinningNumbers(lotto.getNumbers());
    }

    private List<Integer> parseStringToList(String lottoWinningNumbers) {
        List<String> numberList = Arrays.asList(lottoWinningNumbers.split(","));
        return parseStringListToIntegerList(numberList);
    }

    private List<Integer> parseStringListToIntegerList(List<String> numberList) {
        List<Integer> numbers = new ArrayList<>();
        for (String number : numberList) {
            try {
                numbers.add(Integer.parseInt(number));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자만 입력이 가능합니다.");
            }
        }
        return numbers;
    }

    public void inputLottoBonusNumber() {
        System.out.println(System.lineSeparator() + Constant.INPUT_BONUS_NUMBER_MESSAGE);
        String bonusNumber = Console.readLine();
        winningNumberGroup.setBonusNumber(validateBonusNumber(bonusNumber));
    }

    private int validateBonusNumber(String bonusNumber) {
        List<Integer> winningNumbers = winningNumberGroup.getWinningNumbers();
        validator.validateBonusNumber(bonusNumber, winningNumbers);
        return Integer.parseInt(bonusNumber);
    }

    public void checkWinningCount() {
        List<Lotto> lottoNumbers = lottoNumberGroup.getLottoNumbers();
        List<Integer> winningNumbers = winningNumberGroup.getWinningNumbers();
        int bonusNumber = winningNumberGroup.getBonusNumber();
        List<Integer> numberCount;

        for (Lotto lottoNumber : lottoNumbers) {
            numberCount = checkWinningNumberCount(lottoNumber, winningNumbers, bonusNumber);
            setWinningCount(numberCount.get(0), numberCount.get(1));
        }
    }

    private List<Integer> checkWinningNumberCount(Lotto lottoNumbers, List<Integer> winningNumbers, int bonusNumber) {
        int winningNumberCount = 0;
        int bonusNumberCount = 0;
        for (Integer lottoNumber : lottoNumbers.getNumbers()) {
            if (winningNumbers.contains(lottoNumber)) {
                winningNumberCount += 1;
            }

            if (lottoNumber == bonusNumber) {
                bonusNumberCount = 1;
            }
        }
        return new ArrayList<>(Arrays.asList(winningNumberCount, bonusNumberCount));
    }

    private void setWinningCount(int winningNumberCount, int bonusNumberCount) {
        if (winningNumberCount == 3) {
            winningCount.set(0, winningCount.get(0) + 1);
        } else if (winningNumberCount == 4) {
            winningCount.set(1, winningCount.get(1) + 1);
        } else if (winningNumberCount == 5) {
            if (bonusNumberCount == 1) {
                winningCount.set(2, winningCount.get(2) + 1);
            } else {
                winningCount.set(3, winningCount.get(3) + 1);
            }
        } else if (winningNumberCount == 6) {
            winningCount.set(4, winningCount.get(4) + 1);
        }
    }

    public void printWinningStatistics() {
        System.out.println(System.lineSeparator() + Constant.PRINT_WINNING_STATISTICS);
        System.out.println(Constant.DASH);
        LottoWinningCriteria[] a = LottoWinningCriteria.values();
        for (int i = 0 ; i < winningCount.size(); i++) {
            System.out.println(a[i].getMessage(winningCount.get(i)));
        }
    }

    public double getYield() {
        double yield = 0;
        LottoWinningCriteria[] a = LottoWinningCriteria.values();
        for (int i = 0; i < winningCount.size(); i++) {
            yield += a[i].getMoney(winningCount.get(i));
        }
        return Math.round(yield / (lottoCount * Constant.TEN) * 10) / 10.;
    }
}
