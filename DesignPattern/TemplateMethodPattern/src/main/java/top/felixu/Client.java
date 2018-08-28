package top.felixu;

/**
 * @Author felixu
 * @Date 2018.08.16
 */
public class Client {
    public static void main(String[] args) {
        System.out.println(new BlackCardBill().getBill());
        System.out.println(new PlatinumCardBill().getBill());
    }
}
