package com.tung7.docsys.service.impl;

import com.tung7.docsys.entity.DocCategory;
import com.tung7.docsys.entity.DocConfig;
import com.tung7.docsys.repositories.ConfigRepository;
import com.tung7.docsys.service.inf.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public DocConfig save(DocConfig docConfig) {
        return configRepository.save(docConfig);
    }

    @Override
    public List<DocConfig> saveAll(Iterable<DocConfig> iterable) {
        return configRepository.save(iterable);
    }

    @Override
    public Map<String, Serializable> findAll2Map() {
        List<DocConfig> list = configRepository.findAll();

        Map<String, Serializable> rtnMap = new HashMap<>();
        for (DocConfig c : list) {
            rtnMap.put(c.getKey(), c.getValue());
        }
        return rtnMap;
    }
}
