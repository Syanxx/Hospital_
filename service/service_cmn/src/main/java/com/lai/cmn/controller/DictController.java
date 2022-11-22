package com.lai.cmn.controller;


import com.lai.cmn.service.DictService;
import com.lai.model.cmn.Dict;
import com.lai.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/admin/cmn/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @ApiOperation("根据id查询是否有子id")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id){
       List<Dict> list =  dictService.findChildData(id);
       return Result.ok(list);
    }
    //导出数据字典接口
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response){
        dictService.exportDictData(response);
    }

    //导入数据字典接口
    @PostMapping("importData")
    public Result importDict(MultipartFile file){
        dictService.importDictData(file);
        return Result.ok();
    }

    //格局dictcode和value查询
    @GetMapping("getName/{dictCode}/{value}")
    public String getName(@PathVariable String dictCode,
                          @PathVariable String value){
        String dictName = dictService.getDictName(dictCode,value);
        return dictName;
    }
    //根据value查询
    @GetMapping("getName/{value}")
    public String getName(@PathVariable String value){
        String dictName = dictService.getDictName("",value);
        return dictName;
    }
    //根据dictCode获取下级节点
    @ApiOperation(value = "根据dictCode获取下级节点")
    @GetMapping("findByDictCode/{dictCode}")
    public Result findByDictCode(@PathVariable String dictCode){
        List<Dict> list= dictService.findByDictCode(dictCode);
        return Result.ok(list);
    }

}
