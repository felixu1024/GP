package top.felixu.payment;

import top.felixu.entity.Order;
import top.felixu.model.PayResult;

/**
 * @Author felixu
 * @Date 2018.08.09
 */
public interface Payment {

    PayResult pay(Order order);
}
