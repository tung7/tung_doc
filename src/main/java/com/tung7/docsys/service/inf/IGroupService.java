package com.tung7.docsys.service.inf;

import com.tung7.docsys.entity.DocGroup;

import javax.print.Doc;
import java.util.List;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/5/12.
 * @update
 */

public interface IGroupService {

    List<DocGroup> findAllOrderByTaxis();

    DocGroup save(DocGroup docGroup);
}
