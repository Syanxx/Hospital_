package com.lai.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lai.model.order.OrderInfo;
import com.lai.model.order.PaymentInfo;

import java.util.Map;

public interface PaymentService extends IService<PaymentInfo> {
    void savePaymentInfo(OrderInfo order, Integer paymentType);

    void paySuccess(String out_trade_no, Map<String, String> map);

    PaymentInfo getPaymentInfo(Long orderId, Integer paymentType);
}
