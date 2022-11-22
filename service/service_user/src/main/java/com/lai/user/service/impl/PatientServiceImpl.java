package com.lai.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lai.cmn.client.DictFeignClient;
import com.lai.enums.DictEnum;
import com.lai.model.user.Patient;
import com.lai.user.mapper.PatientMapper;
import com.lai.user.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {

    //引入远程调用
    @Autowired
    private DictFeignClient dictFeignClient;

    @Override
    public List<Patient> findAllUserId(Long userId) {
        //根据userid查询所有就诊人信息
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",userId);
        List<Patient> patientList = baseMapper.selectList(wrapper);
        //通过远程调用,得到编码对应内容,查询数据字典内容
        patientList.stream().forEach(item ->{
            this.packPatient(item);
        });
        return patientList;
    }

    @Override
    public Patient getPatientId(Long id) {
        Patient patient = baseMapper.selectById(id);
        return this.packPatient(patient);
    }

    private Patient packPatient(Patient item) {
        String CertificatesTypeString = dictFeignClient.getName(DictEnum.CERTIFICATES_TYPE.getDictCode(), item.getCertificatesType());
        //联系人证件类型
        String contactsCertificatesTypeString =
                dictFeignClient.getName(DictEnum.CERTIFICATES_TYPE.getDictCode(),item.getContactsCertificatesType());
        //省
        String provinceString = dictFeignClient.getName(item.getProvinceCode());
        //市
        String cityString = dictFeignClient.getName(item.getCityCode());
        //区
        String districtString = dictFeignClient.getName(item.getDistrictCode());

        item.getParam().put("certificatesTypeString", CertificatesTypeString);
        item.getParam().put("contactsCertificatesTypeString", contactsCertificatesTypeString);
        item.getParam().put("provinceString", provinceString);
        item.getParam().put("cityString", cityString);
        item.getParam().put("districtString", districtString);
        item.getParam().put("fullAddress", provinceString + cityString + districtString + item.getAddress());
        return item;

    }
}
