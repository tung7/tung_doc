package com.tung7.docsys.rest;

import com.tung7.docsys.entity.DocConfig;
import com.tung7.docsys.service.inf.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/29.
 * @update
 */
@RestController
@RequestMapping("/api/config")
public class ConfigRest {
    private static final Logger logger = LoggerFactory.getLogger(ConfigRest.class);

    @Autowired
    IConfigService configService;

    @RequestMapping("/{key}")
    public DocConfig findByKey(@PathVariable("key") String key) {
        return configService.findByKey(key);
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public DocConfig save(DocConfig docConfig) {
        return configService.save(docConfig);
    }
}
