package com.lws.rawrblogend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

// 接收前端发送的上传文件信息(上传完毕)
@Data
public class FileUploadRequest {

    // 上传文件大小
    @NotBlank(message = "文件名不能为空")
    private String name;

    // 上传文件大小
    private Long size;

    // 文件后缀
    @NotBlank(message = "后缀不能为空")
    private String ext;

}
