package lotto.controller;

import lotto.service.LottoService;

public class LottoController {
    private final LottoService lottoService;

    public LottoController() {
        lottoService = new LottoService();
    }

    public void Start() {
        lottoService.gameStart();
    }
}
