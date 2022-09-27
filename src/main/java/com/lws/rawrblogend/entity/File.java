package com.lws.rawrblogend.entity;

import com.lws.rawrblogend.enums.FileBelong;
import com.lws.rawrblogend.enums.FileStatus;
import com.lws.rawrblogend.enums.FileType;
import com.lws.rawrblogend.enums.Storage;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class File extends BaseEntity {

    private String name;

    private String ext; // 文件后缀

    private Long size; // 文件大小

    private String uri;

    @Enumerated(EnumType.STRING)
    private FileBelong belong; // 设置该文件属于那种模块图片

    @Enumerated(EnumType.STRING)
    private FileType type; // 文件类型

    @Enumerated(EnumType.STRING)
    private Storage storage; // 上传方式

    @Enumerated(EnumType.STRING)
    private FileStatus status = FileStatus.UPLOADING; // 上传状态 默认上传中
}
