package com.tung7.docsys.service.inf;

import com.tung7.docsys.entity.DocConfig;

import javax.print.Doc;
import java.io.Serializable;
import java.util.Iterator;
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

public interface IConfigService {
    DocConfig findById(Long id);

    DocConfig findByKey(String key);

    DocConfig save(DocConfig docConfig);

    List<DocConfig> saveAll(Iterable<DocConfig> iterable);

    Map<String, Serializable> findAll2Map();
}
