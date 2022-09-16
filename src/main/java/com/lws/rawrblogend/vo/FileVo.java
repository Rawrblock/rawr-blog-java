package com.lws.rawrblogend.vo;

import com.lws.rawrblogend.enums.FileStatus;
import com.lws.rawrblogend.enums.FileType;
import com.lws.rawrblogend.enums.Storage;
import lombok.Data;

@Data
public class FileVo extends BaseVo {

    private String name;

    private String ext;

    // 文件上传到oss后返回的url值
    private String uri;

    private Storage storage;

    private Long size;

    private FileType type;

    private FileStatus status;


}
