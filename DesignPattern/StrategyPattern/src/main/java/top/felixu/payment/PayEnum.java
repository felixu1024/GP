package top.felixu.payment;

import top.felixu.entity.Order;
import top.felixu.model.PayResult;

/**
 * @Author felixu
 * @Date 2018.08.09
 */
public enum PayEnum {

    /**
     * 阿里支付
     */
    ALI_APY() {
        @Override
        public PayResult pay(Order order) {
            return new AliPay().pay(order);
        }
    },

    WECHAT_PAY() {
        @Override
        public PayResult pay(Order order) {
            return new WechatPay().pay(order);
        }
    };

    public abstract PayResult pay(Order order);
}
