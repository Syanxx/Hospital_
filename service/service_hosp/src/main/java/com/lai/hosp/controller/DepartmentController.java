package com.lai.hosp.controller;


import com.lai.hosp.service.DepartmentService;
import com.lai.result.Result;
import com.lai.vo.hosp.DepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationSupport;
import java.util.List;

@RestController
@RequestMapping("/admin/hosp/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    //根据医院编号,查询医院所有科室列表
    @GetMapping("getDeptList/{hoscode}")
    public Result getDepList(@PathVariable String hoscode){
        List<DepartmentVo> list = departmentService.findDepTree(hoscode);
        return Result.ok(list);
    }
}
