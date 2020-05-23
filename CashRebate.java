/**
 * Created by lero on 2020/5/23.
 */
public class CashRebate extends CashSuper {
    private double moneyRebate = 1.0;

    public CashRebate(String moneyRebate) {
        this.moneyRebate = Double.parseDouble(moneyRebate);
    }

    @Override
    public double accpetCash(double money) {
        return money * moneyRebate;
    }
}
