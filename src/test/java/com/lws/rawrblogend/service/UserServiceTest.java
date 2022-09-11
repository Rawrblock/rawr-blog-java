package com.lws.rawrblogend.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
class UserServiceTest {

    @Test
    void created() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.ping());
    }
}