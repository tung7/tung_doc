package com.tung7.docsys;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application Entry
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/29.
 * @update
 */
@SpringBootApplication
//@ServletComponentScan
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(Application.class).bannerMode(Banner.Mode.OFF).run(args);
    }
}
