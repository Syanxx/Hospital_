package com.lai.order.service;

import java.util.Map;

public interface WeixinSevice {
    Map createNative(Long orderId);

    Map<String, String> queryPayStatus(Long orderId);

    Boolean refund(Long orderId);
}
