package com.lws.rawrblogend.service.impl;

import com.lws.rawrblogend.dto.FileDto;
import com.lws.rawrblogend.dto.FileFinishUpload;
import com.lws.rawrblogend.dto.FileStorageUploadDto;
import com.lws.rawrblogend.dto.FileUploadRequest;
import com.lws.rawrblogend.entity.File;
import com.lws.rawrblogend.enums.FileStatus;
import com.lws.rawrblogend.enums.Storage;
import com.lws.rawrblogend.exception.BizException;
import com.lws.rawrblogend.exception.ExceptionType;
import com.lws.rawrblogend.mapper.FileMapper;
import com.lws.rawrblogend.respository.FileRepository;
import com.lws.rawrblogend.service.FileService;
import com.lws.rawrblogend.service.FileStorageService;
import com.lws.rawrblogend.utils.FileTypeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    private FileMapper fileMapper;

    private FileRepository fileRepository;

    private FileStorageService fileStorageServices;

    // 改变文件上传状态, 并且将文件的访问地址保存
    @Override
    public FileDto finishUpload(FileFinishUpload fileFinishUpload) {
        Optional<File> fileOptional = fileRepository.findById(fileFinishUpload.getFileId());
        if (!fileOptional.isPresent()) {
            throw new BizException(ExceptionType.FILE_NOT_FOUND);
        }
        // 获取旧文件数据
        File file = fileOptional.get();
        // 改变旧文件状态
        file.setStatus(FileStatus.UPLOADED);
        // 保存文件的访问地址
        file.setUri(fileFinishUpload.getFileUri());
        File saved = fileMapper.updateEntity(file, fileFinishUpload);
        // 保存文件
        File savedFile = fileRepository.save(saved);
        return fileMapper.toDto(savedFile);
    }

    // 获取 OSS的临时stsToken 并返回阿里云上传的配置参数
    // 并且将前端上传的文件信息保存,返回fileId 用于改变状态并返回文件访问地址
    @Override
    @Transactional
    public FileStorageUploadDto initUpload(FileUploadRequest fileUploadRequest) {
        // 判断文件是否已经存在
        Optional<File> oldFile = fileRepository.findByNameAndExtAndSize(fileUploadRequest.getName(), fileUploadRequest.getExt(), fileUploadRequest.getSize());
        if (oldFile.isPresent()) {
            // 当数据库存在该文件,直接获取stsToken返回给前端
            FileStorageUploadDto fileStorageUploadDto = fileStorageServices.initFileUpload();
            fileStorageUploadDto.setFileId(oldFile.get().getId());
            return fileStorageUploadDto;
        } else {
            // 1. 将前端上传后的文件信息 转换为 数据库文件
            File file = fileMapper.createEntity(fileUploadRequest);
            // 2. 手动补充信息
            file.setType(FileTypeTransformer.getFileTypeFromExt(fileUploadRequest.getExt()));
            file.setStorage(getDefaultStorage());
            // 3. 将该文件信息保存到数据库(后续访问)
            File savedFile = fileRepository.save(file);
            // 4. 获取 stsToken临时上传凭证
            FileStorageUploadDto fileStorageUploadDto = fileStorageServices.initFileUpload();
            fileStorageUploadDto.setFileId(savedFile.getId());
            return fileStorageUploadDto;
        }
    }

    @Override
    public List<FileDto> getDefaultFile() {
        List<File> files = fileRepository.findByType();
        List<FileDto> list = files.stream().map(fileMapper::toDto).collect(Collectors.toList());
        return list;
    }

    // 后台设置默认的上传方式
    public Storage getDefaultStorage() {
        return Storage.OSS;
    }

    @Autowired
    public void setFileMapper(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Autowired
    public void setFileRepository(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Autowired
    public void setFileStorageServices(FileStorageService fileStorageServices) {
        this.fileStorageServices = fileStorageServices;
    }
}
