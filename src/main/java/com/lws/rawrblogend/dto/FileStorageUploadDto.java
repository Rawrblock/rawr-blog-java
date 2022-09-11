package com.lws.rawrblogend.dto;

import lombok.Data;

@Data
// OSS sts临时返回凭证字段
public class FileStorageUploadDto {

    // oss keyID
    private String accessKeyId;

    // oss 密钥
    private String accessKeySecret;

    // sts临时凭证
    private String stsToken;

    // 凭证到期时间
    private String expiration;

    // 文件在数据库的id
    private String fileId;
}
