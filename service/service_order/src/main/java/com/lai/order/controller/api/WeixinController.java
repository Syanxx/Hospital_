package com.lai.order.controller.api;


import com.lai.order.service.PaymentService;
import com.lai.order.service.WeixinSevice;
import com.lai.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/order/weixin")
public class WeixinController {

    @Autowired
    private WeixinSevice weixinSevice;

    @Autowired
    private PaymentService paymentService;

    //生成微信支付的二维码
    @GetMapping("createNative/{orderId}")
    public Result createNative(@PathVariable Long orderId){
        Map map = weixinSevice.createNative(orderId);
        return Result.ok(map);
    }

    //查询支付状态
    @GetMapping("queryPayStatus/{orderId}")
    public Result queryPayStatus(@PathVariable Long orderId){
        //查询状态,调用微信接口实现支付状态的查询
        Map<String,String> map = weixinSevice.queryPayStatus(orderId);
        if (map == null){
            return Result.fail().message("支付错误");
        }
        if ("SUCCESS".equals(map.get("trade_state"))){
            //更新订单状态
            String out_trade_no = map.get("out_trade_no");
            paymentService.paySuccess(out_trade_no,map);
            return Result.ok().message("支付成功");
        }
        return Result.ok().message("支付中");
    }
}
