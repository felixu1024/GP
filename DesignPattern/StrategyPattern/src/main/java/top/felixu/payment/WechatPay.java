package top.felixu.payment;

import top.felixu.constants.OrderConstants;
import top.felixu.entity.Order;
import top.felixu.model.PayResult;

/**
 * @Author felixu
 * @Date 2018.08.09
 */
public class WechatPay implements Payment{
    public PayResult pay(Order order) {
        return new PayResult(OrderConstants.Status.PAY_DONE, "使用微信支付了" + order.getPrice() + "元，支付成功！");
    }
}
