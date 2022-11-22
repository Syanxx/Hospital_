package com.lai.hosp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lai.model.hosp.HospitalSet;
import com.lai.vo.order.SignInfoVo;

public interface HospitalSetService extends IService<HospitalSet> {
    String getSignKey(String hoscode);

    SignInfoVo getSignInfoVo(String hoscode);
}
