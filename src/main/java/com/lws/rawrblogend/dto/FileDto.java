package com.lws.rawrblogend.dto;

import com.lws.rawrblogend.enums.FileStatus;
import com.lws.rawrblogend.enums.FileType;
import com.lws.rawrblogend.enums.Storage;
import lombok.Data;

import java.util.Date;

@Data
public class FileDto {
    private String id;

    private String name;

    private String ext;

    private String uri;

    private Long size;

    private FileType type;

    private Storage storage;

    private FileStatus status;

    private Date createdTime;

    private Date updatedTime;

}
