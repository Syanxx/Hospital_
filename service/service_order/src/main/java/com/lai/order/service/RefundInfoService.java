package com.lai.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lai.model.order.PaymentInfo;
import com.lai.model.order.RefundInfo;

public interface RefundInfoService extends IService<RefundInfo> {
    RefundInfo saveRefundInfo(PaymentInfo paymentInfo);

}
