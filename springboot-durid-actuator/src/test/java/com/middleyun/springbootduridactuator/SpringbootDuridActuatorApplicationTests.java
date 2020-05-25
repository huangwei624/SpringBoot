package com.middleyun.springbootduridactuator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class SpringbootDuridActuatorApplicationTests {

    @Autowired
    private DataSource druidDataSource;

    @Test
    void test1() {
        System.out.println(druidDataSource);
    }

}
