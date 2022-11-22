package com.lai.msm.service;

import com.lai.vo.msm.MsmVo;

public interface MsmService {
    boolean send(String phone, String code);

    //mq使用发送短信
    boolean send(MsmVo msmVo);
}
