/**
 * Created by lero on 2020/5/23.
 */
public class CashVIP extends CashSuper{
    private double moneyRebate = 1.0;
    private int creditCondition = 0;
    private int creditReturn = 0;

    public CashVIP(String moneyRebate, String creditCondition, String creditReturn) {
        this.moneyRebate = Double.parseDouble(moneyRebate);
        this.creditCondition = Integer.parseInt(creditCondition);
        this.creditReturn = Integer.parseInt(creditReturn);
    }

    @Override
    public double accpetCash(double money) {
        return money * moneyRebate;
    }

    public int getCredit(double money) {
        int resCredit = 0;
        if(money >= creditCondition){
            resCredit = Math.floorDiv((int) money, creditCondition) * creditReturn;
        }
        return resCredit;

    }
}
