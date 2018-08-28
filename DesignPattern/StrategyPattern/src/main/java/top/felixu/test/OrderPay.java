package top.felixu.test;

import top.felixu.constants.OrderConstants;
import top.felixu.entity.Order;
import top.felixu.model.PayResult;
import top.felixu.payment.AliPay;
import top.felixu.payment.Pay;
import top.felixu.payment.PayEnum;
import top.felixu.payment.WechatPay;

/**
 * @Author felixu
 * @Date 2018.08.09
 */
public class OrderPay {
    public static void main(String[] args) {
        Order order = new Order("1", 23.45, OrderConstants.Status.WAIT_PAY);
//        PayResult result = Pay.pay(new WechatPay(), order);
        PayResult result = PayEnum.WECHAT_PAY.pay(order);
        System.out.println(result.getMsg());
    }
}
