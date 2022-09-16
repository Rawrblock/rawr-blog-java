package com.lws.rawrblogend.respository;

import com.lws.rawrblogend.entity.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, String> {

    Optional<Blog> findById(String id);

    @Query("from Blog blog where blog.status='DRAFT'")
    Slice<Blog> getAllBlogs(Pageable pageable);
}
