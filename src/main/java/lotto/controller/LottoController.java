package lotto.controller;

import lotto.service.LottoService;

public class LottoController {
    private final LottoService lottoService;

    public LottoController() {
        lottoService = new LottoService();
    }

    public void Start() {
        lottoService.gameStart();
        lottoService.inputLottoPurchaseAmounts();
        lottoService.generateLottoNumbers();
        lottoService.printLottoNumbers();
        lottoService.inputLottoWinningNumber();
        lottoService.inputLottoBonusNumber();
    }
}
