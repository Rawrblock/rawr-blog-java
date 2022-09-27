package com.lws.rawrblogend.respository;

import com.lws.rawrblogend.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, String> {

    Optional<File> findById(String id);

    // 查询默认封面图片
    @Query("from File file where file.type='DEFAULT_IMAGE' ORDER BY file.createdTime DESC")
    List<File> findByType();

    // 通过文件名与文件后缀与文件大小比较 判断该文件是否已存在
    @Query("from File file where file.name= ?1 and file.ext = ?2 and file.size = ?3")
    Optional<File> findByNameAndExtAndSize(String name, String ext, Long size);

}
