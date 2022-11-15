package lotto.domain;

public enum LottoWinningCriteria {
    THREE( 5_000, "3개 일치 (5,000원) - "),
    FOUR( 50_000, "4개 일치 (50,000원) - "),
    FIVE(1_500_000, "5개 일치 (1,500,000원) - "),
    FIVE_ALPHA( 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원) - "),
    SIX( 2_000_000_000, "6개 일치 (2,000,000,000원) - ");

    private final int money;
    private final String message;

    LottoWinningCriteria(int money, String message) {
        this.money = money;
        this.message = message;
    }

    public String getMessage(int count) {
        return message + count + "개";
    }
}
