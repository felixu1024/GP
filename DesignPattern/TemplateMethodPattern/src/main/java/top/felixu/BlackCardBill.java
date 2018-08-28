package top.felixu;

/**
 * @Author felixu
 * @Date 2018.08.16
 */
public class BlackCardBill extends Bill {

    @Override
    protected String getCustomerType() {
        return "黑卡";
    }

    @Override
    protected double getMoney() {
        return 234567.89;
    }
}
