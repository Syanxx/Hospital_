package com.lai.msm.controller;


import com.lai.msm.service.MsmService;
import com.lai.msm.utils.RandomUtil;
import com.lai.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/msm")
public class MsmApiController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String ,String> redisTemplate;

    //发送手机验证码
    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone){
        //从redis中获取验证码,如果获取成功返回ok;
        //redis中phone 就是key, 验证码就为value
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return  Result.ok();
        }

        //如果从redis获取不到,生成验证码,
        code = RandomUtil.getSixBitRandom();
        boolean isSend = msmService.send(phone,code);
        //通过短信服务进行发送
        if (isSend){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.ok();
        }
        else {
            return Result.fail().message("发送短信失败");
        }
    }

}
