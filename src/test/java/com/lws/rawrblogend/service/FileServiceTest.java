package com.lws.rawrblogend.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class FileServiceTest {

    @Autowired
    FileService fileService;

    @Autowired
    FileStorageService fileStorageService;

    @Test
    void initUpload() {
//        FileUploadRequest fileUploadRequest = new FileUploadRequest();
//        fileUploadRequest.setName("blog/user/sergi.jpg");
////        fileUploadRequest.setEtag("8B2A3B04F6C6FB961F895ADCDCA827CA");
//        fileUploadRequest.setSize(3000L);
//        fileUploadRequest.setExt("jepg");
//        FileStorageUploadDto fileStorageUploadDto = fileService.initUpload(fileUploadRequest);
//        log.info(fileStorageUploadDto.toString());
////        FileDto fileDto = fileService.finishUpload("123");
//        log.info(fileDto.toString());
//        log.info(fileDto.getUri());
    }

}