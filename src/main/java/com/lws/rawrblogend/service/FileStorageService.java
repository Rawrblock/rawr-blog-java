package com.lws.rawrblogend.service;

import com.lws.rawrblogend.dto.FileStorageUploadDto;

public interface FileStorageService {

    // 通过文件存放目录+文件名  在使用stsToken获取 文件临时访问权限
    String getFileUrl(String fileName);

    // 获取 OSS上传 sts临时凭证
    FileStorageUploadDto initFileUpload();
}
