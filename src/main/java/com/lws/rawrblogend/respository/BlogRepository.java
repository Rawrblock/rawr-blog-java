package com.lws.rawrblogend.respository;

import com.lws.rawrblogend.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, String> {

    Optional<Blog> findById(String id);

}
