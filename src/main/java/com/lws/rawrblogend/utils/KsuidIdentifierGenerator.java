package com.lws.rawrblogend.utils;

import com.github.ksuid.KsuidGenerator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * 生成 ksuid字符串 生成策略
 * 用于：实体类id生成
 */
public class KsuidIdentifierGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return KsuidGenerator.generate();
    }
}
