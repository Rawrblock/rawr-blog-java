package com.lws.rawrblogend.controller;

import com.lws.rawrblogend.dto.FileFinishUpload;
import com.lws.rawrblogend.dto.FileStorageUploadDto;
import com.lws.rawrblogend.dto.FileUploadRequest;
import com.lws.rawrblogend.mapper.FileMapper;
import com.lws.rawrblogend.service.FileService;
import com.lws.rawrblogend.vo.FileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class FileController {

    private FileService fileService;

    private FileMapper fileMapper;

    // 前端文件上传完后 保存到数据库
    @PostMapping("/upload_init")
    public FileStorageUploadDto initUpload(@Validated @RequestBody FileUploadRequest fileUploadRequest) {
        return fileService.initUpload(fileUploadRequest);
    }

    // 通过文件ID修改 文件上传状态
    @PostMapping("/upload_finish")
    public FileVo finishUpload(@RequestBody FileFinishUpload fileFinishUpload) {
        return fileMapper.toVo(fileService.finishUpload(fileFinishUpload));
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setFileMapper(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }
}
