package com.lai.oss.controller;

import com.lai.oss.utils.MinioUtil;
import com.lai.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/oss/file")
public class FileApiCotroller {

    @Autowired
    private MinioUtil minioUtil;

    @Value("${minio.endpoint}")
    private String address;
    @Value("${minio.bucketName}")
    private String bucketName;

    @PostMapping("fileUpload")
    public Result upload(MultipartFile file) {

       String upload = address+"/"+bucketName+"/"+minioUtil.upload(file);

        return Result.ok(upload);
    }

}

