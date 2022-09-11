package com.lws.rawrblogend.respository;

import com.lws.rawrblogend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JPA操作数据库
public interface UserRepository extends JpaRepository<User, String> {

    // 通过邮箱查找用户信息
    Optional<User> findByEmail(String email);

}
