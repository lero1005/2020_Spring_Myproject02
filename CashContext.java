/**
 * Created by lero on 2020/5/23.
 */
public class CashContext {
    private CashSuper cs = null;

    public CashContext(String type) {
        switch (type) {
            case "正常收费":
                cs = new CashNormal();
                break;

            case "打七折":
                CashRebate cr1 = new CashRebate("0.7");
                cs = cr1;
                break;

            case "满100减10":
                CashReturn cr2 = new CashReturn("100", "10");
                cs = cr2;
                break;

            case "VIP用户六折优惠":
                CashVIP cv = new CashVIP("0.6", "100", "10");
                cs = cv;
                break;
        }
    }

    public CashSuper getCs() {
        return cs;
    }

    public void setCs(CashSuper cs) {
        this.cs = cs;
    }

    public double getResult(double money) {
        return cs.accpetCash(money);
    }
}
