package com.code.codegen.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author : Alan
 * @date : 2019/7/16  14:41
 */
@Component
public class MyCommandRunner implements CommandLineRunner {
    @Value("${server.port}")
    private String port;

    @Override
    public void run(String... args) {
        try {
            Runtime.getRuntime().exec("cmd   /c   start   http://localhost:" + port + "/generator/index");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
