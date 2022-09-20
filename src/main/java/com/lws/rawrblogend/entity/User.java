package com.lws.rawrblogend.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Collection;

@Entity
@Data
public class User extends BaseEntity implements UserDetails {

    private String name;

    // 主要使用邮箱登录
    @Column(unique = true)
    private String email;

    private String password;

    // 该账号是否被锁定
    private Boolean locked = false;

    // 该账号是否启动
    private Boolean enabledSymbol = true;

    @OneToOne(cascade = CascadeType.REFRESH)
    private File avatar;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return getName();
    }

    // 用户是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 用户是否被锁定
    @Override
    public boolean isAccountNonLocked() {
        return !getLocked();
    }

    // 凭证是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账号是否启用
    @Override
    public boolean isEnabled() {
        return getEnabledSymbol();
    }
}
