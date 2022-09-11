package com.lws.rawrblogend.respository;

import com.lws.rawrblogend.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, String> {

    Optional<File> findById(String id);

}
