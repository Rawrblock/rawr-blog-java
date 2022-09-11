package com.lws.rawrblogend.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

// 标注该 类变为一个共有的实体类
@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    // 指定生成策略名
    @GeneratedValue(generator = "ksuid")
    // 指定生成策略代码文件
    @GenericGenerator(name = "ksuid", strategy = "com.lws.rawrblogend.utils.KsuidIdentifierGenerator")
    private String id;

    @CreationTimestamp
    private Date createdTime;

    @UpdateTimestamp
    private Date updatedTime;

}
