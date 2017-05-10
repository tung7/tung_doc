package com.tung7.docsys;

import com.tung7.docsys.service.impl.SystemService;
import com.tung7.docsys.service.inf.ISystemService;
import com.tung7.docsys.support.JsonResultArgumentResult;
import com.tung7.docsys.support.utils.DocSystemUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
public class Application implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    @Autowired
    SecurityManager securityManager;

    public static final AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(Application.class).bannerMode(Banner.Mode.OFF).run(args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        synchronized (Application.class) {
            if (integer.intValue() == 0) {
                try {
                    ThreadContext.bind(securityManager);
                    DocSystemUtils.initDB();
                } catch (Exception e) {
                    log.error("数据库初始化失败!", e);
                }
            }
        }
    }
}
