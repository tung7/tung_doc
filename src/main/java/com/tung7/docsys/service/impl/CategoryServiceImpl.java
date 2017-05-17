package com.tung7.docsys.service.impl;

import com.tung7.docsys.entity.DocCategory;
import com.tung7.docsys.repositories.CategoryRepository;
import com.tung7.docsys.repositories.ResourceRepository;
import com.tung7.docsys.service.inf.ICategoryService;
import com.tung7.docsys.support.utils.ParamUtils;
import com.tung7.docsys.support.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
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
@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ResourceRepository resourceRepository;

//    @Override
//    public DocCategory findByIdForDetail(Long id) {
//        DocCategory category = findById(id);
//        /* 遍历获取文章 */
//        category.getArticlesSet();
//        recurseCategoryFetchArticles(category);
//        return category;
//    }
//    private void recurseCategoryFetchArticles(DocCategory category) {
//        if (category.getCategorySet() == null) {
//            return;
//        }
//        for (DocCategory item : category.getCategorySet()) {
//            item.getArticlesSet();
//            recurseCategoryFetchArticles(item);
//        }
//    }

    @Override
    public DocCategory save(DocCategory category) {
        if (category.getId() == null) {
            category.setArticleNum(0L);
            category.setTaxis(Utils.getNextTaxis(resourceRepository.findMaxTaxis()));
        }

        return categoryRepository.save(category);
    }

    @Override
    public DocCategory findByName(String name) {
        return categoryRepository.findByName(name);
    }

//    @Override
//    public Map<String, Long> findSubForMap(Long id) {
//        Map<String, Long> map = new LinkedHashMap<>();
//
//        // 获取全部
//        if (id == 0L) {
//            List<DocCategory> list  = categoryRepository.findByParentIsNull();
//            for (DocCategory item : list) {
//                getCategoriesForMapWithRecurse(item, "", map);
//            }
//        } else {
//            ParamUtils.checkId(id);
//            DocCategory category = categoryRepository.findOne(id);
//            getCategoriesForMapWithRecurse(category, "", map);
//        }
//        return map;
//    }
//
//    private void getCategoriesForMapWithRecurse(DocCategory category, String prefix, Map<String, Long> map) {
//        map.put(prefix + category.getName(), category.getId());
//        for (DocCategory item : category.getCategorySet()) {
//            getCategoriesForMapWithRecurse(item, prefix + "-- ", map);
//        }
//    }


//    @Override
//    public Map<String, Long> findAllForMap() {
//        return findSubForMap(0L);
//    }


    @Override
    public DocCategory findById(Long id) {
        ParamUtils.checkId(id);
        DocCategory category = categoryRepository.findOne(id);

        return category;
    }

}
