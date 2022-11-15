package lotto.domain;

import lotto.Lotto;

import java.util.List;

public class LottoNumberGroup {
    private List<Lotto> lottoNumbers;

    public List<Lotto> getLottoNumbers() {
        return lottoNumbers;
    }

    public void setLottoNumbers(List<Lotto> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }
}
