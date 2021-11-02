package com.tokentest;

import com.tokentest.utils.TransformCurrentTimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TokenTestApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public static void main(String[] args) {
        System.out.println(TransformCurrentTimeUtil.returnCurrentTime());
    }

}
