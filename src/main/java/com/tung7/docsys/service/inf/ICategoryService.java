package com.tung7.docsys.service.inf;

import com.tung7.docsys.entity.DocCategory;

import java.util.List;
import java.util.Map;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/10.
 * @update
 */

public interface ICategoryService {
    DocCategory findById(Long id);

//    DocCategory findByIdForDetail(Long id);

    DocCategory save(DocCategory category);

    DocCategory findByName(String name);

//    Map<String, Long> findSubForMap(Long id);
//    Map<String, Long> findAllForMap();

}
