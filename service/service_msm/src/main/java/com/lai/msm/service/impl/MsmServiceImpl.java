package com.lai.msm.service.impl;


import com.lai.msm.service.MsmService;
import com.lai.msm.utils.SmsSendUtils;
import com.lai.vo.msm.MsmVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class MsmServiceImpl implements MsmService {

    @Autowired
    private SmsSendUtils smsSendUtils;
    @Override
    public boolean send(String phone, String code) {
        //判断手机号是否为空
        if(StringUtils.isEmpty(phone)) {
            return false;
        }
        boolean isTrue = smsSendUtils.smsSend(phone,code);
        if (isTrue){
            return true;
        }
        return false;
    }

    //mq发送短信封装
    @Override
    public boolean send(MsmVo msmVo) {
        //判断手机号是否为空
        if(!StringUtils.isEmpty(msmVo.getPhone())) {
            String code = (String) msmVo.getParam().get("code");
            boolean isSend = this.send(msmVo.getPhone(), code);
            return isSend;
        }

        return false;
    }

}
