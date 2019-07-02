package com.zyp.weixinsell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 邹
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogTest {
    @Test
    public void test() {
        String name="zxas";
        String password="123456";

        log.debug("debug...");
        log.info("name:"+name+"password"+password);
        log.info("name:{},password:{}",name,password);
        log.error("error...");
        log.warn("warn...");
    }
}
