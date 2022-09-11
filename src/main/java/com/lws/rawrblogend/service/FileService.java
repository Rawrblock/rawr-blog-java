package com.lws.rawrblogend.service;

import com.lws.rawrblogend.dto.FileDto;
import com.lws.rawrblogend.dto.FileFinishUpload;
import com.lws.rawrblogend.dto.FileStorageUploadDto;
import com.lws.rawrblogend.dto.FileUploadRequest;

// 前端文件处理
public interface FileService {

    // 获取凭证信息
    FileStorageUploadDto initUpload(FileUploadRequest fileUploadRequest);

    FileDto finishUpload(FileFinishUpload fileFinishUpload);
}
