package com.tung7.docsys;

import com.tung7.docsys.support.JsonResultArgumentResult;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

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
public class Application extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(Application.class).bannerMode(Banner.Mode.OFF).run(args);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new JsonResultArgumentResult());
    }
}
