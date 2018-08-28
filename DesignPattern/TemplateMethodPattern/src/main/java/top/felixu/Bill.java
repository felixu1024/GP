package top.felixu;

/**
 * @Author felixu
 * @Date 2018.08.16
 */
public abstract class Bill {

    protected abstract String getCustomerType();

    protected abstract double getMoney();

    public String getBill() {
        return String.format("尊贵的" + getCustomerType() + "客户，您本月的账单金额为：%.2f元", getMoney());
    }
}
