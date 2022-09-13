package com.lws.rawrblogend.entity;

import com.lws.rawrblogend.enums.BlogStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Data
@Entity
public class Blog extends BaseEntity {

    private String title; // 标题

    private String content; // 内容

    @OneToOne
    private File cover; // 封面

    @Enumerated(EnumType.STRING)
    private BlogStatus status;


}
