package com.tung7.docsys.service.impl;

import com.tung7.docsys.entity.DocConfig;
import com.tung7.docsys.repositories.ConfigRepository;
import com.tung7.docsys.service.inf.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/29.
 * @update
 */
@Service
@Transactional
public class ConfigServiceImpl implements IConfigService {
    @Autowired
    private ConfigRepository configRepository;

    public DocConfig findById(Long id) {
        return configRepository.findOne(id);
    }

    public DocConfig findByKey(String key) {
        return configRepository.findByKey(key);
    }

    @Transactional(readOnly = false)
    public DocConfig save(DocConfig docConfig) {
        return configRepository.save(docConfig);
    }
}
