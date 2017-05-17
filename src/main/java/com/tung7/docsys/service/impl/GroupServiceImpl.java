package com.tung7.docsys.service.impl;

import com.tung7.docsys.entity.DocGroup;
import com.tung7.docsys.repositories.GroupRepository;
import com.tung7.docsys.service.inf.IGroupService;
import com.tung7.docsys.support.excpetion.NullNameException;
import com.tung7.docsys.support.utils.ParamUtils;
import com.tung7.docsys.support.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/12.
 * @update
 */
@Service
@Transactional
public class GroupServiceImpl implements IGroupService {

    @Autowired
    GroupRepository groupRepository;

    @Override
    public List<DocGroup> findAllOrderByTaxis() {
        List<DocGroup> list = groupRepository.findAllOrderByTaxisAsc();
        /* 好像不get一下也行，，说好的懒加载呢。。。。 */
//        for (DocGroup group : list) {
//            group.getTopCategories();
//        }
        return list;
    }

    @Override
    public DocGroup save(DocGroup docGroup) {
        if (docGroup.getName() == null) {
            throw new NullNameException("分组名为null, 请输入分组名称。");
        }
        if (docGroup.getTaxis() == null) {
            docGroup.setTaxis(Utils.getNextTaxis(groupRepository.findMaxTaxis()));
        }
        return groupRepository.save(docGroup);
    }
}
