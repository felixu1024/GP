package top.felixu;

/**
 * @Author felixu
 * @Date 2018.08.16
 */
public class PlatinumCardBill extends Bill {
    @Override
    protected String getCustomerType() {
        return "白金卡";
    }

    @Override
    protected double getMoney() {
        return 1000.34;
    }
}
